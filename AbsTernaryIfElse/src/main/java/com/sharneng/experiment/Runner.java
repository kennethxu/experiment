package com.sharneng.experiment;

import com.codahale.metrics.Snapshot;

public class Runner {
    public static final int MEASURED_RUN = 100;

    protected static void runAndReport(Fixture[] fixtures, int loopCount) {
        // warn up
        for (int j = 20; j > 0; j--) {
            for (Fixture f : fixtures)
                f.run();
        }

        // run and measure it
        for (int j = MEASURED_RUN; j > 0; j--) {
            for (Fixture f : fixtures)
                f.runMeasured();
        }

        // report
        System.out.println("Method,    Mean,   Min,   Max, Median,  75%");
        for (Fixture f : fixtures) {
            final Snapshot s = f.histogram.getSnapshot();
            final double n = loopCount;
            System.out.println(String.format("%-8s,%6.1f,%6.1f,%6.1f,%6.1f,%6.1f", f.name, s.getMean() / n, s.getMin()
                    / n, s.getMax() / n, s.getMedian() / n, s.get75thPercentile() / n));
        }
    }

}