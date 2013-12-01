package com.sharneng.algorithm;

import java.util.Comparator;

/**
 * Defines the interface to compare two primitive float values. Implementation can extend the {@link Base} class for
 * simplicity.
 * 
 * @author Kenneth Xu
 * 
 */
public interface FloatComparator extends Comparator<Float> {
    /**
     * Compares the order of two float values.
     * 
     * @param x
     *            the first float value to be compared
     * @param y
     *            the second float value to be compared
     * @return negative if x is in front of y, zero if x is equal to y and positive if y is in front of x
     */
    int compare(float x, float y);

    /**
     * An abstract implementation that helps to ease the work of implementing {@link IntComparator} interface.
     * 
     * @author Kenneth Xu
     * 
     */
    public abstract class Base implements FloatComparator {
        @Override
        public int compare(Float x, Float y) {
            return compare(x.floatValue(), y.floatValue());
        }
    }

    /**
     * A comparator that sorts the float value in ascending order.
     */
    static FloatComparator ASCENDING = new Base() {
        @Override
        public int compare(float x, float y) {
            return (int) Math.signum(x - y);
        }
    };

    /**
     * A comparator that sorts the float value in descending order.
     */
    static FloatComparator DESCENDING = new Base() {
        @Override
        public int compare(float x, float y) {
            return (int) Math.signum(y - x);
        }
    };
}
