package com.sharneng.experiment.median;

import java.util.PriorityQueue;

/**
 * A class can be used to obtain the median value of all integers inserted into it.
 * 
 * @author Ken
 * 
 */
public class MedianFinder {

    private interface Heap {
        int peak();

        void add(int i);

        int poll();

        int swap(int i);
    }

    private class MinHeap implements Heap {
        // uses priority queue for simplicity but I would use my own heap implementation
        // to avoid boxing/unboxing and provide optimal swap.
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

        @Override
        public int peak() {
            return queue.peek();
        }

        @Override
        public void add(final int i) {
            queue.add(i);
        }

        @Override
        public int poll() {
            return queue.poll();
        }

        @Override
        public int swap(final int i) {
            int poll = poll();
            add(i);
            return poll;
        }
    }

    private class MaxHeap extends MinHeap {
        @Override
        public int peak() {
            return -super.peak();
        }

        @Override
        public void add(final int i) {
            super.add(-i);
        }

        @Override
        public int poll() {
            return -super.poll();
        }
    }

    private static final int ZERO = 0;
    private static final int ODD = 1;
    private static final int EVEN = 2;

    private int count = 0;
    private final Heap left = new MaxHeap();
    private int median;
    private final Heap right = new MinHeap();

    /**
     * Gets the median value of all inserted elements.
     * 
     * @return the median value of all inserted elements.
     */
    public int getMedian() {
        if (count == 0) throw new IllegalStateException("No element");
        return count % 2 == ODD ? median : (left.peak() + right.peak()) / 2;
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
            median = i < left.peak() ? left.swap(i) : i > right.peak() ? right.swap(i) : i;
            return;
        case ODD:
            if (i <= median) {
                left.add(i);
                right.add(median);
            } else {
                left.add(median);
                right.add(i);
            }
            return;
        }
    }

    private static int parity(final int i) {
        return (i - 1) % 2 + 1;
    }
}