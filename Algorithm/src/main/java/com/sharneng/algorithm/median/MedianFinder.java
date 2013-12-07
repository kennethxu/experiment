package com.sharneng.algorithm.median;

import com.sharneng.algorithm.Scending;
import com.sharneng.algorithm.heap.IntHeap;

/**
 * A class can be used to obtain the median value of all integers inserted into it.
 * 
 * @author Kenneth Xu
 * 
 */
public class MedianFinder {

    private static final int ZERO = 0;
    private static final int ODD = 1;
    private static final int EVEN = 2;

    private int count = 0;
    private final IntHeap left = new IntHeap().setScending(Scending.DESCENDING);
    private int median;
    private final IntHeap right = new IntHeap();

    /**
     * Gets the median value of all inserted elements.
     * 
     * @return the median value of all inserted elements.
     */
    public int getMedian() {
        if (count == 0) throw new IllegalStateException("No element");
        return count % 2 == ODD ? median : (left.peek() + right.peek()) / 2;
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
            median = i < left.peek() ? left.pollAndOffer(i) : i > right.peek() ? right.pollAndOffer(i) : i;
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
