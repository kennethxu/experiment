package com.sharneng.ds.heap;

import com.sharneng.algorithm.IntComparator;
import com.sharneng.algorithm.Scending;

import java.util.Arrays;
// SUPPRESS CHECKSTYLE UnusedImport FOR 4 LINES BECAUSE they are used in JavaDoc
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import javax.annotation.CheckForNull;

/**
 * /** An unbounded priority {@linkplain Queue queue} of {@link Integer} based on a {@link IntHeap}. The elements of the
 * priority queue are ordered according to their natural ordering, or by an {@link IntComparator} and the
 * {@link Scending} setting provided at queue construction time, depending on which constructor is used. A priority
 * queue does not permit {@code null} elements.
 * 
 * <p>
 * The <em>head</em> of this queue is the <em>least</em> element when ascending, or <em>most</em> element when
 * descending with respect to the specified ordering. If multiple elements are tied for least (or most) value, the head
 * is one of those elements -- ties are broken arbitrarily. The queue retrieval operations {@link #poll},
 * {@link #remove},{@link #dequeue}, {@link @swap}, {@link #peek}, and {@link #element} access the element at the head
 * of the queue.
 * 
 * <p>
 * A priority queue is unbounded, but has an internal <i>capacity</i> governing the size of an array used to store the
 * elements on the queue. It is always at least as large as the queue size. As elements are added to a priority queue,
 * its capacity grows automatically. The details of the growth policy are not specified.
 * 
 * <p>
 * This class and its iterator implement all of the <em>optional</em> methods of the {@link Collection} and
 * {@link Iterator} interfaces. The Iterator provided in method {@link #iterator()} is <em>not</em> guaranteed to
 * traverse the elements of the priority queue in any particular order. If you need ordered traversal, consider using
 * {@code Arrays.sort(pq.toArray())}.
 * 
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> Multiple threads should not access a
 * {@code IntPriorityQueue} instance concurrently if any of the threads modifies the queue. Instead, use the thread-safe
 * {@link java.util.concurrent.PriorityBlockingQueue} class.
 * 
 * <p>
 * This implementation is different from {@link java.util.PriorityQueue PriorityQueue&lt;Integer>} is that this
 * implementation uses {@link IntHeap} which in turn uses primitive int array, which is more efficient than
 * {@code java.util.PriorityQueue}'s object array.
 * 
 * <p>
 * Implementation note: this implementation provides O(log(n)) time for the enqueing and dequeing methods (
 * {@link #enqueue}, {@link #offer}, {@link #add}, {@link #poll}, {@link #remove} and {@link #dequeue} ); linear time
 * for the {@link #remove(int)}, {@link #remove(Object)}, {@link #contains(int)} and {@link #contains(Object)} methods;
 * and constant time for the retrieval methods ( {@link #peek}, {@link #element}, {@link #head} and {@link #size} ).
 * 
 * @author Kenneth Xu mostly copied from Open JDK implementation
 * 
 */
public final class IntPriorityQueue extends AbstractPriorityQueue<Integer> {

    private final IntHeap heap;

    /**
     * Creates a {@code IntPriorityQueue} with the default initial capacity (11) that orders its elements according to
     * natural ascending ordering.
     */
    public IntPriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY, null, Scending.ASCENDING);
    }

    /**
     * Creates a {@code IntPriorityQueue} with the default initial capacity (11) that orders its elements according to
     * the ascending order specified comparator.
     * 
     * @param comparator
     *            the comparator that will be used to order this priority queue. If {@code null}, the natural ordering
     *            will be used.
     */
    public IntPriorityQueue(@CheckForNull IntComparator comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator, Scending.ASCENDING);
    }

    /**
     * Creates a {@code IntPriorityQueue} with the default initial capacity (11) that orders its elements according to
     * the order specified by scending parameter.
     * 
     * @param scending
     *            specify if the queue is by ascending or descending order
     */
    public IntPriorityQueue(Scending scending) {
        this(DEFAULT_INITIAL_CAPACITY, null, scending);
    }

    /**
     * Creates a {@code IntPriorityQueue} with the specified initial capacity that orders its elements according to
     * their natural ascending ordering.
     * 
     * @param initialCapacity
     *            the initial capacity for this priority queue
     * @throws IllegalArgumentException
     *             if {@code initialCapacity} is less than 0
     */
    public IntPriorityQueue(int initialCapacity) {
        this(initialCapacity, null, Scending.ASCENDING);
    }

    /**
     * Creates a {@code IntPriorityQueue} with the specified initial capacity that orders its elements according to the
     * ascending order specified comparator.
     * 
     * @param initialCapacity
     *            the initial capacity for this priority queue
     * @param comparator
     *            the comparator that will be used to order this priority queue. If {@code null}, the natural ordering
     *            will be used.
     * @throws IllegalArgumentException
     *             if {@code initialCapacity} is less than 0
     */
    public IntPriorityQueue(int initialCapacity, @CheckForNull IntComparator comparator) {
        this(initialCapacity, comparator, Scending.ASCENDING);
    }

    /**
     * Creates a {@code IntPriorityQueue} with the specified initial capacity that orders its elements according to the
     * order specified by scending parameter.
     * 
     * @param initialCapacity
     *            the initial capacity for this priority queue
     * @param scending
     *            specify if the queue is by ascending or descending order
     * @throws IllegalArgumentException
     *             if {@code initialCapacity} is less than 0
     */
    public IntPriorityQueue(int initialCapacity, Scending scending) {
        this(initialCapacity, null, scending);
    }

    /**
     * Creates a {@code IntPriorityQueue} with the specified initial capacity that orders its elements according to the
     * order specified by the scending and comparator parameter.
     * 
     * 
     * @param initialCapacity
     *            the initial capacity for this priority queue
     * @param scending
     *            specify if the queue is by ascending or descending order
     * @param comparator
     *            the comparator that will be used to order this priority queue. If {@code null}, the natural ordering
     *            will be used.
     * @throws IllegalArgumentException
     *             if {@code initialCapacity} is less than 0
     */
    public IntPriorityQueue(int initialCapacity, @CheckForNull IntComparator comparator, Scending scending) {
        if (initialCapacity < 0) throw new IllegalArgumentException();
        heap = new IntHeap(new int[initialCapacity]).setComparator(comparator).setScending(scending);
    }

    /**
     * Inserts the specified element into this priority queue.
     * 
     * @param e
     *            the element to be inserted
     * 
     * @return {@code true} (as specified by {@link Queue#offer})
     * @throws ClassCastException
     *             if the specified element cannot be compared with elements currently in this priority queue according
     *             to the priority queue's ordering
     * @throws NullPointerException
     *             if the specified element is null
     */
    @Override
    public boolean offer(Integer e) {
        enqueue(e.intValue());
        return true;
    }

    /**
     * Inserts the specified value into this priority queue.
     * 
     * @param value
     *            the value to be inserted to the priority queue
     */
    public void enqueue(int value) {
        modCount++;
        heap.add(value);
    }

    @Override
    @CheckForNull
    public Integer peek() {
        return heap.size > 0 ? heap.array[0] : null;
    }

    /**
     * Retrieves, but does not remove, the head of this queue. This method differs from {@link #peek peek} only in that
     * it throws an exception if this queue is empty.
     * 
     * @return the head of this queue
     * @throws NoSuchElementException
     *             if this queue is empty
     */
    @Override
    public Integer element() {
        return heap.root();
    }

    /**
     * Retrieves, but does not remove, the head of this queue. This method differs from {@link #element} only in that it
     * returns primitive <tt>int</tt>.
     * 
     * @return the head of this queue
     * @throws NoSuchElementException
     *             if this queue is empty
     */
    public int head() {
        return heap.root();
    }

    /**
     * Removes a single instance of the specified element from this queue, if it is present. More formally, removes an
     * element {@code e} such that {@code o.equals(e)}, if this queue contains one or more such elements. Returns
     * {@code true} if and only if this queue contained the specified element (or equivalently, if this queue changed as
     * a result of the call).
     * 
     * @param o
     *            element to be removed from this queue, if present
     * @return {@code true} if this queue changed as a result of the call
     */
    @Override
    public boolean remove(@CheckForNull Object o) {
        return o != null && (o instanceof Integer) && remove(((Integer) o).intValue());
    }

    /**
     * Removes the given value from this queue, if it is present. If this queue contains one or more elements with same
     * value, only one of the elements will be removed. Returns {@code true} if and only if this queue contained the
     * specified value (or equivalently, if this queue changed as a result of the call).
     * 
     * @param value
     *            value to be removed from this queue, if present
     * @return {@code true} if removed
     */
    public boolean remove(int value) {
        return heap.remove(value);
    }

    /**
     * Returns {@code true} if this queue contains the specified element. More formally, returns {@code true} if and
     * only if this queue contains at least one element {@code e} such that {@code o.equals(e)}.
     * 
     * @param o
     *            object to be checked for containment in this queue
     * @return {@code true} if this queue contains the specified element
     */
    @Override
    public boolean contains(@CheckForNull Object o) {
        return o != null && (o instanceof Integer) && contains(((Integer) o).intValue());
    }

    /**
     * Returns {@code true} if this queue contains the specified value. More formally, returns {@code true} if and only
     * if this queue contains at least one element {@code e} with the given value.
     * 
     * @param value
     *            value to be checked for containment in this queue
     * @return {@code true} if this queue contains the specified value
     */
    public boolean contains(int value) {
        return heap.contains(value);
    }

    /**
     * Returns an array containing all of the values in this queue. The values are in no particular order.
     * 
     * <p>
     * The returned array will be "safe" in that no references to it are maintained by this queue. (In other words, this
     * method must allocate a new array). The caller is thus free to modify the returned array.
     * 
     * @return an array containing all of the values in this queue
     */
    public int[] toIntArray() {
        return Arrays.copyOf(heap.array, heap.size);
    }

    @Override
    public int size() {
        return heap.size;
    }

    /**
     * Removes all of the values from this priority queue. The queue will be empty after this call returns.
     */
    @Override
    public void clear() {
        modCount++;
        heap.size = 0;
    }

    @Override
    @CheckForNull
    public Integer poll() {
        modCount++;
        return heap.size == 0 ? null : heap.remove();
    }

    /**
     * Retrieves and removes the head of this queue. This method differs from {@link #poll} only in that it throws an
     * exception if this queue is empty.
     * 
     * @return the head of this queue
     * @throws NoSuchElementException
     *             if this queue is empty
     */
    @Override
    public Integer remove() {
        return dequeue();
    }

    /**
     * Retrieves and removes the head of this queue. This method differs from {@link #remove} only in that it returns
     * the primitive <tt>int</tt>.
     * 
     * @return the head of this queue
     * @throws NoSuchElementException
     *             if this queue is empty
     */
    public int dequeue() {
        modCount++;
        return heap.remove();
    }

    /**
     * Retrieves and removes the head of this queue and inserts the specified element to the queue in one single
     * operation. This is functionally equivalent to calling {@link #remove()} followed by {@link #add(Integer)}, but
     * this method typically has better performance.
     * 
     * @param e
     *            the element to add after removing the head
     * @return the head of this queue
     * @throws NoSuchElementException
     *             if this queue is empty
     */
    public Integer swap(Integer e) {
        return swap(e.intValue());
    }

    /**
     * Retrieves and removes the head of this queue and inserts the specified value to the queue in one single
     * operation. This is functionally equivalent to calling {@link #dequeue()} followed by {@link #enqueue(int)}, but
     * this method typically has better performance.
     * 
     * @param value
     *            the new value to add after removing the head
     * @return the head of this queue
     * @throws NoSuchElementException
     *             if this queue is empty
     */
    public int swap(int value) {
        modCount++;
        return heap.swap(value);
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
        return heap.remove(o.intValue());
    }
}
