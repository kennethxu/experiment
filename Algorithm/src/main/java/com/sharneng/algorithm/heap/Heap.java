package com.sharneng.algorithm.heap;

import com.sharneng.algorithm.ArrayUtils;
import java.util.Comparator;
import com.sharneng.algorithm.Utils;

import java.util.Arrays;

import javax.annotation.CheckForNull;

/**
 * An unbounded long binary heap. The elements of the heap are ordered according to their natural ordering, or by an
 * {@link Comparator} property, and the scending property.
 * 
 * <p>
 * The <em>root</em> of this heap is the first element of the underlying array. It is the <em>least</em> element when
 * ascending, or <em>most</em> element when descending, with respect to the specified ordering. If multiple elements are
 * tied for least value when ascending, or most value when descending, the root is one of those elements -- ties are
 * broken arbitrarily. The heap retrieval operations {@code swap}, and {@code element} access the element at the root of
 * the heap.
 * 
 * <p>
 * A heap is unbounded, but has an internal <i>capacity</i> governing the size of an array used to store the elements on
 * the heap. It is always at least as large as the heap size. As elements are added to a heap, its capacity grows
 * automatically. The details of the growth policy are not specified.
 * 
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> Multiple threads should not access a
 * {@code LongHeap} instance concurrently if any of the threads modifies the heap.
 * 
 * <p>
 * Implementation note: this implementation provides O(log(n)) time for the inserting and removing methods, e.g.
 * {@code remove()} and {@code add}. linear time for the {@code remove(E)} and {@code contains(E)} methods; and constant
 * time for the retrieval methods ({@code element}, and {@code size}). This implementation of heap data structure
 * provides the various heap operations with little protection. This is a sharp knife, you get great flexibility to
 * manage the heap and in the meantime you can corrupt the structure if not careful. {@link LongPriorityQueue} provides
 * a better protected queue implementation based on {@link Heap}.
 * 
 * <p>
 * This class provide sift methods to deal with one element at a time, heapify method to reorder the underlying array
 * into a binary heap, and sort method to sort the underlying array using the heap sort.
 * 
 * @author Kenneth Xu copied and adapted from open JDK implementation
 * @param <E>
 *            type of the element in the heap
 */
public final class Heap<E> extends AbstractHeap<Heap<E>> {
    E[] array;
    @CheckForNull
    private Comparator<? super E> comparator;

    /**
     * Construct a zero size heap with default capacity.
     */
    @SuppressWarnings("unchecked")
    public Heap() {
        array = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    /**
     * Construct a zero size heap with given array as storage.
     * 
     * @param array
     *            the array to be used as underlying heap storage
     */
    public Heap(final E[] array) {
        this.array = Utils.argumentNotNull(array, "array");
    }

    /**
     * Gets the underlying array of the heap.
     * 
     * @return the underlying array of the heap
     */
    public E[] getArray() {
        return array;
    }

    /**
     * Sets the underlying array of the heap and set the size to be the length of given array.
     * <p>
     * <i>Caution</i>: If the heap was heapified before this method call, it may become no longer heapified.
     * 
     * @param array
     *            the array to set
     * @return this object itself for fluent calls
     */
    public Heap<E> setArray(E[] array) {
        this.array = Utils.argumentNotNull(array, "array");
        size = array.length;
        return this;
    }

    @Override
    int getArrayLength() {
        return array.length;
    }

    /**
     * Gets the comparator used to compare the elements in the heap, or null if natural ordering is used.
     * 
     * @return the comparator of the heap or null if no comparator was set
     */
    @Override
    @CheckForNull
    public Comparator<? super E> getComparator() {
        return comparator;
    }

    /**
     * Sets the comparator to be used to compare the elements in the heap, or null to use natural ordering.
     * <p>
     * <i>Caution</i>: If the heap was heapified before this method call, it may become no longer heapified.
     * 
     * @param comparator
     *            the comparator to set or null
     * @return this object itself for fluent calls
     */
    public Heap<E> setComparator(@CheckForNull Comparator<? super E> comparator) {
        this.comparator = comparator;
        setAscending(ordering.isAscending());
        return this;
    }

    @Override
    void grow(int minCapacity) {
        array = Arrays.copyOf(array, ArrayUtils.newCapacity(getArrayLength(), minCapacity));
    }

    /**
     * Gets, but does not remove, the root of this heap.
     * 
     * @return the root of this heap
     * @throws java.util.NoSuchElementException
     *             when heap is empty
     */
    public E root() {
        ensureNotEmpty();
        return array[0];
    }

    /**
     * Gets and removes the root of this heap.
     * 
     * @return the root of this heap.
     * @throws java.util.NoSuchElementException
     *             when heap is empty
     */
    public E remove() {
        ensureNotEmpty();
        int s = --size;
        // modCount++;
        E result = array[0];
        E x = array[s];
        if (s != 0) siftDown(0, x);
        return result;
    }

    /**
     * Gets and removes the root of this heap, and in the meantime adds the new element to the heap, keeping the heap
     * size consistent.
     * 
     * @param value
     *            the value to swap with the root of heap
     * @return the root of this heap.
     * @throws java.util.NoSuchElementException
     *             when heap is empty
     */
    public E swap(E value) {
        ensureNotEmpty();
        // modCount++;
        E result = array[0];
        siftDown(0, value);
        return result;
    }

    /**
     * Inserts the specified element into this heap.
     * 
     * @param value
     *            the element to be added to heap
     */
    public void add(E value) {
        // modCount++;
        int i = size;
        if (i >= getArrayLength()) grow(i + 1);
        size = i + 1;
        if (i == 0) array[0] = value;
        else siftUp(i, value);
    }

    /**
     * Returns {@code true} if this heap contains the specified value.
     * 
     * @param value
     *            value to be checked for containment in this heap
     * @return {@code true} if this heap contains the specified value
     */
    public boolean contains(@CheckForNull final Object value) {
        return indexOf(value) != -1;
    }

    /**
     * Removes the specified value from the heap.
     * 
     * @param value
     *            value of the element to be removed from this heap, if present
     * @return {@code true} if removed
     */
    public boolean remove(@CheckForNull final Object value) {
        int i = indexOf(value);
        if (i == -1) return false;
        removeAt(i);
        return true;
    }

    private int indexOf(@CheckForNull final Object value) {
        for (int i = 0; i < size; i++)
            if (array[i].equals(value)) return i;
        return -1;
    }

    /**
     * Removes the ith element from heap.
     * 
     * @param i
     *            the index of the element in the heap to be removed
     * @return Normally this method leaves the elements at up to i-1, inclusive, untouched. Under these circumstances,
     *         it returns -1. Occasionally, in order to maintain the heap invariant, it must swap a later element of the
     *         list with one earlier than i. Under these circumstances, this method returns the position of the element
     *         that was previously at the end of the list and is now at some position before i.
     * 
     */
    public int removeAt(int i) {
        ensureNotEmpty();
        int s = --size;
        if (s != i) { // if not removed last element
            E moved = array[s];
            siftDown(i, moved);
            if (array[i] == moved) {
                int index = siftUp(i, moved);
                if (array[i] != moved) return index;
            }
        }
        return -1;
    }

    /**
     * Sorts the elements in the heap using heap sort algorithm according to the scending and comparator properties.
     */
    public void sort() {
        final int originalSize = size;
        final Ordering originalOrdering = ordering;
        setAscending(!originalOrdering.isAscending());
        try {
            heapify();
            switch (ordering) {
            default:
            case NATURAL_ASC:
                for (int i = originalSize - 1; i > 0; i--) {
                    E v = array[i];
                    array[i] = array[0];
                    size = i;
                    siftDownNatural(0, v);
                }
                return;
            case NATURAL_DESC:
                for (int i = originalSize - 1; i > 0; i--) {
                    E v = array[i];
                    array[i] = array[0];
                    size = i;
                    siftDownNaturalDesc(0, v);
                }
                return;
            case COMPARATOR_ASC:
                for (int i = originalSize - 1; i > 0; i--) {
                    E v = array[i];
                    array[i] = array[0];
                    size = i;
                    siftDownComparator(0, v);
                }
                return;
            case COMPARATOR_DESC:
                for (int i = originalSize - 1; i > 0; i--) {
                    E v = array[i];
                    array[i] = array[0];
                    size = i;
                    siftDownComparatorDesc(0, v);
                }
                return;
            }
        } finally {
            size = originalSize;
            ordering = originalOrdering;
        }
    }

    /**
     * Establishes the heap invariant (described above) in the entire tree, assuming nothing about the order of the
     * elements prior to the call.
     */
    public void heapify() {
        final int middle = (size >>> 1) - 1;
        switch (ordering) {
        default:
        case NATURAL_ASC:
            for (int i = middle; i >= 0; i--)
                siftDownNatural(i, array[i]);
            return;
        case NATURAL_DESC:
            for (int i = middle; i >= 0; i--)
                siftDownNaturalDesc(i, array[i]);
            return;
        case COMPARATOR_ASC:
            for (int i = middle; i >= 0; i--)
                siftDownComparator(i, array[i]);
            return;
        case COMPARATOR_DESC:
            for (int i = middle; i >= 0; i--)
                siftDownComparatorDesc(i, array[i]);
            return;
        }
    }

    /**
     * Inserts element of given value at position index, maintaining heap invariant by promoting the new element up the
     * tree until it is greater than or equal to its parent, or is the root.
     * 
     * @param index
     *            the position to fill
     * @param value
     *            the value of the new element to insert
     * @return the position where the new element eventually lands
     */
    public int siftUp(int index, final E value) {
        switch (ordering) {
        default:
        case NATURAL_ASC:
            return siftUpNatural(index, value);
        case NATURAL_DESC:
            return siftUpNaturalDesc(index, value);
        case COMPARATOR_ASC:
            return siftUpComparator(index, value);
        case COMPARATOR_DESC:
            return siftUpComparatorDesc(index, value);
        }
    }

    private int siftUpNatural(int index, final E value) {
        @SuppressWarnings("unchecked")
        final Comparable<? super E> v = (Comparable<? super E>) value;
        while (index > 0) {
            int parent = (index - 1) >>> 1;
            E e = array[parent];
            if (v.compareTo(e) >= 0) break;
            array[index] = e;
            index = parent;
        }
        array[index] = value;
        return index;
    }

    private int siftUpComparator(int index, final E value) {
        assert comparator != null;
        while (index > 0) {
            int parent = (index - 1) >>> 1;
            E e = array[parent];
            if (comparator.compare(value, e) >= 0) break;
            array[index] = e;
            index = parent;
        }
        array[index] = value;
        return index;
    }

    private int siftUpNaturalDesc(int index, final E value) {
        @SuppressWarnings("unchecked")
        final Comparable<? super E> v = (Comparable<? super E>) value;
        while (index > 0) {
            int parent = (index - 1) >>> 1;
            E e = array[parent];
            if (v.compareTo(e) <= 0) break;
            array[index] = e;
            index = parent;
        }
        array[index] = value;
        return index;
    }

    private int siftUpComparatorDesc(int index, final E value) {
        assert comparator != null;
        while (index > 0) {
            int parent = (index - 1) >>> 1;
            E e = array[parent];
            if (comparator.compare(value, e) <= 0) break;
            array[index] = e;
            index = parent;
        }
        array[index] = value;
        return index;
    }

    /**
     * Inserts new element of given value at position index, maintaining heap invariant by demoting the element down the
     * tree repeatedly until it is less than or equal to its children or is a leaf.
     * 
     * @param index
     *            the position to fill
     * @param value
     *            the value of new element to insert
     * @return the position where the new element eventually land
     */
    public int siftDown(int index, final E value) {
        switch (ordering) {
        default:
        case NATURAL_ASC:
            return siftDownNatural(index, value);
        case NATURAL_DESC:
            return siftDownNaturalDesc(index, value);
        case COMPARATOR_ASC:
            return siftDownComparator(index, value);
        case COMPARATOR_DESC:
            return siftDownComparatorDesc(index, value);
        }
    }

    @SuppressWarnings("unchecked")
    private int siftDownNatural(int index, final E value) {
        final Comparable<? super E> v = (Comparable<? super E>) value;
        int half = size >>> 1;
        while (index < half) {        // loop while a non-leaf
            int child = (index << 1) + 1; // assume left child is least
            E c = array[child];
            int right = child + 1;
            if (right < size && ((Comparable<? super E>) c).compareTo(array[right]) > 0) c = array[child = right];
            if (v.compareTo(c) <= 0) break;
            array[index] = c;
            index = child;
        }
        array[index] = value;
        return index;
    }

    private int siftDownComparator(int index, final E value) {
        assert comparator != null;
        int half = size >>> 1;
        while (index < half) {        // loop while a non-leaf
            int child = (index << 1) + 1; // assume left child is least
            E c = array[child];
            int right = child + 1;
            if (right < size && comparator.compare(c, array[right]) > 0) c = array[child = right];
            if (comparator.compare(value, c) <= 0) break;
            array[index] = c;
            index = child;
        }
        array[index] = value;
        return index;
    }

    @SuppressWarnings("unchecked")
    private int siftDownNaturalDesc(int index, final E value) {
        final Comparable<? super E> v = (Comparable<? super E>) value;
        int half = size >>> 1;
        while (index < half) {        // loop while a non-leaf
            int child = (index << 1) + 1; // assume left child is largest
            E c = array[child];
            int right = child + 1;
            if (right < size && ((Comparable<? super E>) c).compareTo(array[right]) < 0) c = array[child = right];
            if (v.compareTo(c) >= 0) break;
            array[index] = c;
            index = child;
        }
        array[index] = value;
        return index;
    }

    private int siftDownComparatorDesc(int index, final E value) {
        assert comparator != null;
        int half = size >>> 1;
        while (index < half) {        // loop while a non-leaf
            int child = (index << 1) + 1; // assume left child is largest
            E c = array[child];
            int right = child + 1;
            if (right < size && comparator.compare(c, array[right]) < 0) c = array[child = right];
            if (comparator.compare(value, c) >= 0) break;
            array[index] = c;
            index = child;
        }
        array[index] = value;
        return index;
    }

}
