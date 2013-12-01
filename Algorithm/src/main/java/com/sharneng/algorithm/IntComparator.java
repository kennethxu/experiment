package com.sharneng.algorithm;

import java.util.Comparator;

/**
 * Defines the interface to compare two primitive int values. Implementation can extend the {@link Base} class for
 * simplicity.
 * 
 * @author Kenneth Xu
 * 
 */
public interface IntComparator extends Comparator<Integer> {
    /**
     * Compares the order of two int values.
     * 
     * @param x
     *            the first int value to be compared
     * @param y
     *            the second int value to be compared
     * @return negative if x is in front of y, zero if x is equal to y and positive if y is in front of x
     */
    int compare(int x, int y);

    /**
     * An abstract implementation that helps to ease the work of implementing {@link IntComparator} interface.
     * 
     * @author Kenneth Xu
     * 
     */
    public abstract class Base implements IntComparator {
        @Override
        public int compare(Integer x, Integer y) {
            return compare(x.intValue(), y.intValue());
        }
    }

    /**
     * A comparator that sorts the int value in ascending order.
     */
    static IntComparator ASCENDING = new Base() {
        @Override
        public int compare(int x, int y) {
            return x - y;
        }
    };

    /**
     * A comparator that sorts the int value in descending order.
     */
    static IntComparator DESCENDING = new Base() {
        @Override
        public int compare(int x, int y) {
            return y - x;
        }
    };
}
