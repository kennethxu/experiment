package com.sharneng.algorithm;

import java.util.Comparator;

/**
 * Defines the interface to compare two primitive long values. Implementation can extend the {@link Base} class for
 * simplicity.
 * 
 * @author Kenneth Xu
 * 
 */
public interface LongComparator extends Comparator<Long> {
    /**
     * Compares the order of two long values.
     * 
     * @param x
     *            the first long value to be compared
     * @param y
     *            the second long value to be compared
     * @return negative if x is in front of y, zero if x is equal to y and positive if y is in front of x
     */
    int compare(long x, long y);

    /**
     * An abstract implementation that helps to ease the work of implementing {@link IntComparator} interface.
     * 
     * @author Kenneth Xu
     * 
     */
    public abstract class Base implements LongComparator {
        @Override
        public int compare(Long x, Long y) {
            return compare(x.longValue(), y.longValue());
        }
    }

    /**
     * A comparator that sorts the long value in ascending order.
     */
    static LongComparator ASCENDING = new Base() {
        @Override
        public int compare(long x, long y) {
            return Long.signum(x - y);
        }
    };

    /**
     * A comparator that sorts the long value in descending order.
     */
    static LongComparator DESCENDING = new Base() {
        @Override
        public int compare(long x, long y) {
            return Long.signum(y - x);
        }
    };
}
