package com.sharneng.algorithm;

import java.util.Comparator;

/**
 * Defines the interface to compare two primitive double values. Implementation can extend the {@link Base} class for
 * simplicity.
 * 
 * @author Kenneth Xu
 * 
 */
public interface DoubleComparator extends Comparator<Double> {
    /**
     * Compares the order of two double values.
     * 
     * @param x
     *            the first double value to be compared
     * @param y
     *            the second double value to be compared
     * @return negative if x is in front of y, zero if x is equal to y and positive if y is in front of x
     */
    int compare(double x, double y);

    /**
     * An abstract implementation that helps to ease the work of implementing {@link IntComparator} interface.
     * 
     * @author Kenneth Xu
     * 
     */
    public abstract class Base implements DoubleComparator {
        @Override
        public int compare(Double x, Double y) {
            return compare(x.doubleValue(), y.doubleValue());
        }
    }

    /**
     * A comparator that sorts the double value in ascending order.
     */
    static DoubleComparator ASCENDING = new Base() {
        @Override
        public int compare(double x, double y) {
            return (int) Math.signum(x - y);
        }
    };

    /**
     * A comparator that sorts the double value in descending order.
     */
    static DoubleComparator DESCENDING = new Base() {
        @Override
        public int compare(double x, double y) {
            return (int) Math.signum(y - x);
        }
    };
}
