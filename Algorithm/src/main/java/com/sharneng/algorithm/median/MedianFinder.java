package com.sharneng.algorithm.median;

import com.sharneng.algorithm.SortOrder;
import com.sharneng.algorithm.heap.IntegerHeap;

/**
 * A class can be used to obtain the median value of all integers inserted into it.
 * 
 * @author Ken
 * 
 */
public class MedianFinder {

    private static final int ZERO = 0;
    private static final int ODD = 1;
    private static final int EVEN = 2;

    private int count = 0;
    private final IntegerHeap left = new IntegerHeap(SortOrder.DESCENDING);
    private int median;
    private final IntegerHeap right = new IntegerHeap();

    /**
     * Gets the median value of all inserted elements.
     * 
     * @return the median value of all inserted elements.
     */
    public int getMedian() {
        if (count == 0) throw new IllegalStateException("No element");
        return count % 2 == ODD ? median : (left.peekInt() + right.peekInt()) / 2;
    }

    /**
     * Add a new element to the collection that can later be used to determine the median.
     * 
     * @param i
     *            the element to be added
     */
    public void insert(final int i) {
        switch (parity(count++)) {
        case ZERO:
            median = i;
            return;
        case EVEN:
            median = i < left.peekInt() ? left.pollAndOffer(i) : i > right.peekInt() ? right.pollAndOffer(i) : i;
            return;
        case ODD:
            if (i <= median) {
                left.offer(i);
                right.offer(median);
            } else {
                left.offer(median);
                right.offer(i);
            }
            return;
        }
    }

    private static int parity(final int i) {
        return (i - 1) % 2 + 1;
    }
}