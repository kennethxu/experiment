import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;


public class S3MGet {
	private static final long K = 1024, M = K * K, G = M * K;
	private static final long ss = 1000, mm = 60 * ss, hh = 60 * mm;
		
	public static void main(String[] args) throws Exception {
		if (args.length < 2) printUsageAndExit();
		
		boolean testMode = false;
		boolean quietMode = false;
		int parallelism = 50;
		int sleepTime = 0;
		int idle = 0;
		
		int index = 0;
		while(args[index].charAt(0) == '-') {
			String options = args[index++];
			for(int i = 1; i < options.length(); i++) {
				char c = options.charAt(i);
				switch(c) {
				case 't':
					testMode = true;
					break;
				case 'q':
					quietMode = true;
					break;
				case 'p':
				case 'w':
				case 'i':
					int value;
					if (++i < options.length()) {
						value = Integer.parseInt(options.substring(i));
					} else if (args.length > index){
						value = Integer.parseInt(args[index++]);
					} else {
						printUsageAndExit();
						value = 0;
					}
					i = options.length();
					switch(c) {
					case 'p': parallelism = value; break;
					case 'w': sleepTime = value; break;
					case 'i': idle = value; break;
					}
					break;
				default:
					printUsageAndExit();
				}
			}
		}
		if (args.length < index+2) printUsageAndExit();
	
		String bucketName = args[index++];
		String prefix = args[index++];
		
		new S3MGet(bucketName, parallelism, sleepTime, idle, testMode, quietMode).downlad(prefix);
	}
	
	private static void printUsageAndExit() {
		System.out.println("Usage: java -jar S3ParallelGet.jar [-t] [-q] [-w wait-time] [-p parallelism] [-i max-idle] bucket prefix");
		System.exit(1);
	}
	
	private static String formatSize(double size) {
		return 	size < K ? String.format("%1.0fB", size) : 
				size < M ? String.format("%1.0fKB", size / K) :
				size < G ? String.format("%1.0fMB", size / M) :
						   String.format("%1.0fGB", size / G);
	}

	private final String bucketName;
	private final int parallelism;
	private final int sleepTime;
	private final boolean testMode;
	private final boolean quietMode;

	private final AtomicLong totalTime = new AtomicLong(0);
	private final AtomicLong totalSize = new AtomicLong(0);
	private final ClientConfiguration clientConfig;
	private final AmazonS3 s3;
	private ExecutorService executorService;

	private S3MGet(String bucketName, int parallelism, int sleepTime, int idle, boolean testMode, boolean quietMode) {
		this.bucketName = bucketName;
		this.parallelism = parallelism;
		this.sleepTime = sleepTime;
		this.testMode = testMode;
		this.quietMode = quietMode;
		clientConfig = new ClientConfiguration();
		clientConfig.setMaxConnections(parallelism);
		if (idle>0) clientConfig.setConnectionMaxIdleMillis(idle);
		s3 = new AmazonS3Client(clientConfig);
	}

	private void downlad(String prefix) throws InterruptedException, IOException {
		executorService = Executors.newFixedThreadPool(parallelism);
		
		executeParallel(prefix, key->downloadSingle(key));
		
		executorService.shutdown();
		executorService.awaitTermination(60, TimeUnit.SECONDS);
		
		if(quietMode) return;
		
		long time = totalTime.get();
		long size = totalSize.get();
		double speed = size * 1000.0 / time;
		System.out.format("Total S3 Time: %d:%02d:%02d.%03d, Size: %s, Speed: %sps\n",
				time/hh, time%hh/mm, time%mm/ss, time%ss/10, formatSize(size), formatSize(speed)); 
	}
	
	private void executeParallel(String prefix, Consumer<S3ObjectSummary> action) throws InterruptedException, IOException {
		final ListObjectsV2Request req = new ListObjectsV2Request()
				.withBucketName(bucketName)
				.withPrefix(prefix)
				.withMaxKeys(parallelism)
				;
	    ListObjectsV2Result result;
	    do {
	        result = s3.listObjectsV2(req);

	        Set<Path> dirs = new HashSet<Path>();
	        for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
	    	    String name = objectSummary.getKey();
	    	    File file = new File(name);
	    	    Path path = file.getParentFile().toPath();
	    	    if (!dirs.contains(path)) {
	    	        Files.createDirectories(path);
	    	    	dirs.add(path);
	    	    }
	    	    executorService.execute(()-> action.accept(objectSummary));
	        }
	        if (sleepTime > 0) Thread.sleep(sleepTime);
	        req.setContinuationToken(result.getNextContinuationToken());
	    } while(result.isTruncated() == true );
	}

	private void downloadSingle(S3ObjectSummary objectSummary) {
		String keyName = objectSummary.getKey();
		long start = System.currentTimeMillis();
		try(S3Object o = s3.getObject(bucketName, keyName)) {
			try (S3ObjectInputStream s3is = o.getObjectContent()) {
				byte[] read_buf = new byte[1024];
				if(testMode) {
					while (s3is.read(read_buf) > 0);
				} else try(FileOutputStream fos = new FileOutputStream(new File(keyName))) {
					int read_len = 0;
					while ((read_len = s3is.read(read_buf)) > 0) {
					    fos.write(read_buf, 0, read_len);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		if(quietMode) return;
		long elapsed = System.currentTimeMillis() - start;
		totalTime.addAndGet(elapsed);
		totalSize.addAndGet(objectSummary.getSize());
        System.out.format("%s (size:%6s, Time:%5dms)\n", keyName, formatSize(objectSummary.getSize()), elapsed);
	}
}
