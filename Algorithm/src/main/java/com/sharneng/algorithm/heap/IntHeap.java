package com.sharneng.algorithm.heap;

import com.sharneng.algorithm.ArrayUtils;
import com.sharneng.algorithm.IntComparator;
import com.sharneng.algorithm.Utils;

import java.util.Arrays;
import java.util.NoSuchElementException;

public final class IntHeap extends AbstractHeap<IntHeap> {
    int[] array;
    private IntComparator comparator;

    public IntHeap() {
        array = new int[DEFAULT_INITIAL_CAPACITY];
    }

    public IntHeap(final int[] array) {
        setArray(array);
    }

    public int[] getArray() {
        return array;
    }

    public IntHeap setArray(int[] array) {
        this.array = Utils.argumentNotNull(array, "array");
        if (size > array.length) size = array.length;
        return this;
    }

    @Override
    protected int getArrayLength() {
        return array.length;
    }

    @Override
    public IntComparator getComparator() {
        return comparator;
    }

    public IntHeap setComparator(IntComparator comparator) {
        this.comparator = comparator;
        setAscending(ordering.isAscending());
        return this;
    }

    private void grow(int minCapacity) {
        array = Arrays.copyOf(array, ArrayUtils.newCapacity(getArrayLength(), minCapacity));
    }

    public int peek() {
        ensureNotEmpty();
        return array[0];
    }

    public int poll() {
        ensureNotEmpty();
        int s = --size;
        // modCount++;
        int result = array[0];
        int x = array[s];
        if (s != 0) siftDown(0, x);
        return result;
    }

    public int pollAndOffer(int e) {
        ensureNotEmpty();
        // modCount++;
        int result = array[0];
        siftDown(0, e);
        return result;
    }

    private void ensureNotEmpty() {
        if (size == 0) throw new NoSuchElementException();
    }

    public boolean offer(int e) {
        // modCount++;
        int i = size;
        if (i >= getArrayLength()) grow(i + 1);
        size = i + 1;
        if (i == 0) array[0] = e;
        else siftUp(i, e);
        return true;
    }

    /**
     * Version of remove using reference equality, not equals. Needed by iterator.remove.
     * 
     * @param o
     *            element to be removed from this queue, if present
     * @return {@code true} if removed
     */
    public boolean removeEq(int o) {
        for (int i = 0; i < size; i++) {
            if (o == array[i]) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the ith element from heap.
     * 
     * Normally this method leaves the elements at up to i-1, inclusive, untouched. Under these circumstances, it
     * returns null. Occasionally, in order to maintain the heap invariant, it must swap a later element of the list
     * with one earlier than i. Under these circumstances, this method returns the element that was previously at the
     * end of the list and is now at some position before i. This fact is used by iterator.remove so as to avoid missing
     * traversing elements.
     */
    public int removeAt(int i) {
        ensureNotEmpty();
        int s = --size;
        if (s != i) { // if not removed last element
            int moved = array[s];
            siftDown(i, moved);
            if (array[i] == moved) {
                int index = siftUp(i, moved);
                if (array[i] != moved) return index;
            }
        }
        return -1;
    }

    public void sort() {
        final int originalSize = size;
        final Ordering originalOrdering = ordering;
        setAscending(!originalOrdering.isAscending());
        try {
            heapify();
            switch (ordering) {
            case NATURAL_ASC:
                for (int i = originalSize - 1; i > 0; i--) {
                    int v = array[i];
                    array[i] = array[0];
                    size = i;
                    siftDownNatural(0, v);
                }
                return;
            case NATURAL_DESC:
                for (int i = originalSize - 1; i > 0; i--) {
                    int v = array[i];
                    array[i] = array[0];
                    size = i;
                    siftDownNaturalDesc(0, v);
                }
                return;
            case COMPARATOR_ASC:
                for (int i = originalSize - 1; i > 0; i--) {
                    int v = array[i];
                    array[i] = array[0];
                    size = i;
                    siftDownComparator(0, v);
                }
                return;
            case COMPARATOR_DESC:
                for (int i = originalSize - 1; i > 0; i--) {
                    int v = array[i];
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

    public void heapify() {
        final int middle = (size >>> 1) - 1;
        switch (ordering) {
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

    public int siftUp(int index, final int value) {
        switch (ordering) {
        case NATURAL_ASC:
        default:
            return siftUpNatural(index, value);
        case NATURAL_DESC:
            return siftUpNaturalDesc(index, value);
        case COMPARATOR_ASC:
            return siftUpComparator(index, value);
        case COMPARATOR_DESC:
            return siftUpComparatorDesc(index, value);
        }
    }

    private int siftUpNatural(int index, final int value) {
        while (index > 0) {
            int parent = (index - 1) >>> 1;
            int e = array[parent];
            if (value >= e) break;
            array[index] = e;
            index = parent;
        }
        array[index] = value;
        return index;
    }

    private int siftUpComparator(int index, final int value) {
        while (index > 0) {
            int parent = (index - 1) >>> 1;
            int e = array[parent];
            if (comparator.compare(value, e) >= 0) break;
            array[index] = e;
            index = parent;
        }
        array[index] = value;
        return index;
    }

    private int siftUpNaturalDesc(int index, final int value) {
        while (index > 0) {
            int parent = (index - 1) >>> 1;
            int e = array[parent];
            if (value <= e) break;
            array[index] = e;
            index = parent;
        }
        array[index] = value;
        return index;
    }

    private int siftUpComparatorDesc(int index, final int value) {
        while (index > 0) {
            int parent = (index - 1) >>> 1;
            int e = array[parent];
            if (comparator.compare(value, e) <= 0) break;
            array[index] = e;
            index = parent;
        }
        array[index] = value;
        return index;
    }

    public void siftDown(int index, final int value) {
        switch (ordering) {
        case NATURAL_ASC:
            siftDownNatural(index, value);
            return;
        case NATURAL_DESC:
            siftDownNaturalDesc(index, value);
            return;
        case COMPARATOR_ASC:
            siftDownComparator(index, value);
            return;
        case COMPARATOR_DESC:
            siftDownComparatorDesc(index, value);
            return;
        }
    }

    private void siftDownNatural(int index, final int value) {
        int half = size >>> 1;
        while (index < half) {        // loop while a non-leaf
            int child = (index << 1) + 1; // assume left child is least
            int c = array[child];
            int right = child + 1;
            if (right < size && c > array[right]) c = array[child = right];
            if (value <= c) break;
            array[index] = c;
            index = child;
        }
        array[index] = value;
    }

    private void siftDownComparator(int index, final int value) {
        int half = size >>> 1;
        while (index < half) {        // loop while a non-leaf
            int child = (index << 1) + 1; // assume left child is least
            int c = array[child];
            int right = child + 1;
            if (right < size && comparator.compare(c, array[right]) > 0) c = array[child = right];
            if (comparator.compare(value, c) <= 0) break;
            array[index] = c;
            index = child;
        }
        array[index] = value;
    }

    private void siftDownNaturalDesc(int index, final int value) {
        int half = size >>> 1;
        while (index < half) {        // loop while a non-leaf
            int child = (index << 1) + 1; // assume left child is largest
            int c = array[child];
            int right = child + 1;
            if (right < size && c < array[right]) c = array[child = right];
            if (value >= c) break;
            array[index] = c;
            index = child;
        }
        array[index] = value;
    }

    private void siftDownComparatorDesc(int index, final int value) {
        int half = size >>> 1;
        while (index < half) {        // loop while a non-leaf
            int child = (index << 1) + 1; // assume left child is largest
            int c = array[child];
            int right = child + 1;
            if (right < size && comparator.compare(c, array[right]) < 0) c = array[child = right];
            if (comparator.compare(value, c) >= 0) break;
            array[index] = c;
            index = child;
        }
        array[index] = value;
    }

}
