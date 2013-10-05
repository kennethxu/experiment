package com.sharneng.experiment;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

abstract class Fixture {
    private static final MetricRegistry metrics = new MetricRegistry();

    static final int source[] = new int[] { 3, -3949, -5, 4439858, 9298, -939848498, 320848848, -932, 927719849,
            -39875849, 1 };
    static final int target[] = new int[source.length];
    static final int ELEMENT_END = source.length - 2;

    final String name;
    final Histogram histogram;

    Fixture() {
        this.name = this.getClass().getSimpleName();
        this.histogram = metrics.histogram(name);
    }

    abstract void run();

    void runMeasured() {
        long start = System.nanoTime();
        run();
        histogram.update(System.nanoTime() - start);
    }
}