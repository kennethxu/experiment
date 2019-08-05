package com.sharneng.fun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
/**
 * a pair of number randomly generated again and again, each between 0 and 1.
    What is the average difference between them? 1/3
    What is the probability that the difference is between 1/3 and 1/2? 7/36
 */
public class DistanceOfRandom implements Runnable {
	double totalLength = 0.0;
	long sixthCount = 0;
	AtomicLong count = new AtomicLong(0);
	Random random = new Random();
	static AtomicBoolean go = new AtomicBoolean(true);
	
	
	public static void main(String[] args) throws IOException {
		DistanceOfRandom runner = new DistanceOfRandom();
		Thread t = new Thread(runner);
		t.start();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		do {
			String s = in.readLine();
			if (!s.isEmpty() && s.charAt(0) == 'q') {
				go.set(false);
				try {
					t.join(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			runner.print();
			
		} while (go.get());
	}

	private void print() {
		double totalCount = count.get();
		System.out.println("Count: " + totalCount + " Average: " + totalLength/totalCount + " sixth: " + 1.0 * sixthCount / totalCount);
	}

	@Override
	public void run() {
		double low = 1.0/3.0;
		double high = 1.0/2.0;
		while (go.get()) {
			double x = random.nextDouble();
			double y = random.nextDouble();
			double distance = Math.abs(x-y);
			totalLength += distance;
			if (low < distance && distance < high) sixthCount++;
			count.incrementAndGet();
		}
	}

}
