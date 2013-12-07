package com.sharneng.algorithm.heap;

import com.sharneng.algorithm.IntComparator;
import com.sharneng.algorithm.Scending;

/**
 * 
 * 
 * @author Kenneth Xu
 * 
 */
public final class IntPriorityQueue extends AbstractPriorityQueue<Integer> {
    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    final private IntHeap heap;

    public IntPriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY, null, null);
    }

    public IntPriorityQueue(IntComparator comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator, null);
    }

    public IntPriorityQueue(Scending scending) {
        this(DEFAULT_INITIAL_CAPACITY, null, scending);
    }

    public IntPriorityQueue(int initCapacity) {
        this(initCapacity, null, null);
    }

    public IntPriorityQueue(int initCapacity, IntComparator comparator) {
        this(initCapacity, comparator, null);
    }

    public IntPriorityQueue(int initCapacity, Scending scending) {
        this(initCapacity, null, scending);
    }

    public IntPriorityQueue(int initCapacity, IntComparator comparator, Scending scending) {
        heap = new IntHeap(new int[initCapacity]).setComparator(comparator).setScending(scending);
    }

    @Override
    public int size() {
        return heap.getSize();
    }

    @Override
    public Integer peek() {
        return heap.getSize() > 0 ? heap.getArray()[0] : null;
    }

    public int peekInt() {
        return heap.peek();
    }

    @Override
    public Integer poll() {
        modCount++;
        return heap.getSize() == 0 ? null : heap.poll();
    }

    public int pollInt() {
        modCount++;
        return heap.poll();
    }

    @Override
    public boolean offer(Integer e) {
        modCount++;
        return heap.offer(e);
    }

    public boolean offer(int e) {
        modCount++;
        return heap.offer(e);
    }

    public Integer pollAndOffer(Integer e) {
        modCount++;
        return heap.pollAndOffer(e);
    }

    public int pollAndOffer(int e) {
        modCount++;
        return heap.pollAndOffer(e);
    }

    /**
     * Version of remove using reference equality, not equals. Needed by iterator.remove.
     * 
     * @param o
     *            element to be removed from this queue, if present
     * @return {@code true} if removed
     */
    protected boolean removeEq(int o) {
        return heap.removeEq(o);
    }

    /*
     * Removes the ith element from queue.
     * 
     * Normally this method leaves the elements at up to i-1, inclusive, untouched. Under these circumstances, it
     * returns null. Occasionally, in order to maintain the heap invariant, it must swap a later element of the list
     * with one earlier than i. Under these circumstances, this method returns the element that was previously at the
     * end of the list and is now at some position before i. This fact is used by iterator.remove so as to avoid missing
     * traversing elements.
     */
    @Override
    protected Integer removeAt(int i) {
        assert i >= 0 && i < heap.size;
        modCount++;
        int index = heap.removeAt(i);
        return index < 0 ? null : heap.array[index];
    }

    @Override
    protected Integer elementAt(int i) {
        assert i >= 0 && i < heap.size;
        return heap.array[i];
    }

    @Override
    protected boolean removeEq(Integer o) {
        return heap.removeEq(o.intValue());
    }
}
