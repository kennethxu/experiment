package com.sharneng.algorithm.heap;

import com.sharneng.algorithm.Scending;

import java.util.Comparator;

/**
 * Base class to simplify the implementation of heaps of various types.
 * 
 * @author Kenneth Xu
 * 
 * @param <T>
 *            type of the sub class
 */
abstract class AbstractHeap<T extends AbstractHeap<T>> {

    protected static final int DEFAULT_INITIAL_CAPACITY = 11;
    // SUPPRESS CHECKSTYLE VisibilityModifier FOR 2 LINES BECAUSE used by final sub class only.
    protected Ordering ordering = Ordering.NATURAL_ASC;
    protected int size;

    /**
     * Gets the {@link Scending} setting of the heap.
     * 
     * @return value of {@link Scending} to indicate the order of the heap
     */
    public Scending getScending() {
        return ordering.isAscending() ? Scending.ASCENDING : Scending.DESCENDING;
    }

    @SuppressWarnings("unchecked")
    public T setScending(Scending scending) {
        setAscending(scending != Scending.DESCENDING);
        return (T) this;
    }

    public int getSize() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public T setSize(int size) {
        final int length = getArrayLength();
        this.size = length < size ? length : size;
        return (T) this;
    }

    protected final void setAscending(boolean isAsc) {
        ordering = (getComparator() == null) ? (isAsc ? Ordering.NATURAL_ASC : Ordering.NATURAL_DESC)
                : (isAsc ? Ordering.COMPARATOR_ASC : Ordering.COMPARATOR_DESC);
    }

    protected abstract Comparator<?> getComparator();

    protected abstract int getArrayLength();

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
