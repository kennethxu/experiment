package com.sharneng.algorithm.heap;

import com.sharneng.algorithm.Scending;

import java.util.Comparator;
import java.util.NoSuchElementException;

import javax.annotation.CheckForNull;

/**
 * Base class to simplify the implementation of heaps of various types.
 * 
 * @author Kenneth Xu
 * 
 * @param <T>
 *            type of the sub class
 */
abstract class AbstractHeap<T extends AbstractHeap<T>> {

    static final int DEFAULT_INITIAL_CAPACITY = 11;
    Ordering ordering = Ordering.NATURAL_ASC;
    int size;

    /**
     * Gets the {@link Scending} setting of the heap.
     * 
     * @return value of {@link Scending} to indicate the order of the heap
     */
    public Scending getScending() {
        return ordering.isAscending() ? Scending.ASCENDING : Scending.DESCENDING;
    }

    /**
     * Sets the {@link Scending} setting of the heap. The first element of the heap will be the minimal value when set
     * to {@link Scending#ASCENDING}, or the maximal value when set to {@link Scending#DESCENDING}.
     * <p>
     * <i>Caution</>: If the heap was heapified before this method call, it may become no longer heapified.
     * 
     * @param scending
     *            the value to set
     * @return this object itself for fluent calls
     */
    @SuppressWarnings("unchecked")
    public T setScending(Scending scending) {
        setAscending(scending != Scending.DESCENDING);
        return (T) this;
    }

    /**
     * Gets the size of the heap.
     * 
     * @return the size of the heap
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of the heap. The underlying array may grow when necessary to accommodate the required size.
     * <p>
     * <i>Caution</>: If the heap was heapified before this method call, it may become no longer heapified.
     * 
     * @param size
     *            the size to set
     * @return this object itself for fluent calls
     */
    @SuppressWarnings("unchecked")
    public T setSize(int size) {
        final int length = getArrayLength();
        if (size > length) grow(size);
        this.size = size;
        return (T) this;
    }

    final void setAscending(boolean isAsc) {
        ordering = (getComparator() == null) ? (isAsc ? Ordering.NATURAL_ASC : Ordering.NATURAL_DESC)
                : (isAsc ? Ordering.COMPARATOR_ASC : Ordering.COMPARATOR_DESC);
    }

    /**
     * @throws NoSuchElementException
     *             if heap is empty
     */
    void ensureNotEmpty() {
        if (size == 0) throw new NoSuchElementException();
    }

    @CheckForNull
    abstract Comparator<?> getComparator();

    abstract int getArrayLength();

    abstract void grow(int minCapacity);

    enum Ordering {
        NATURAL_ASC(false, true),
        NATURAL_DESC(false, false),
        COMPARATOR_ASC(true, true),
        COMPARATOR_DESC(true, false);

        private final boolean isComparator;
        private final boolean isAscending;

        private Ordering(boolean isComparator, boolean isAscending) {
            this.isComparator = isComparator;
            this.isAscending = isAscending;
        }

        boolean isAscending() {
            return isAscending;
        }

        boolean isComparator() {
            return isComparator;
        }
    }
}
