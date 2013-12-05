package com.sharneng.algorithm.heap;

import com.sharneng.algorithm.ArrayUtils;
import com.sharneng.algorithm.IntComparator;
import com.sharneng.algorithm.SortOrder;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class IntegerHeap implements Heap<Integer> {
    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    final private Sifter sifter;

    public IntegerHeap() {
        this(DEFAULT_INITIAL_CAPACITY, null, null);
    }

    public IntegerHeap(IntComparator comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator, null);
    }

    public IntegerHeap(SortOrder order) {
        this(DEFAULT_INITIAL_CAPACITY, null, order);
    }

    public IntegerHeap(int initCapacity) {
        this(initCapacity, null, null);
    }

    public IntegerHeap(int initCapacity, IntComparator comparator) {
        this(initCapacity, comparator, null);
    }

    public IntegerHeap(int initCapacity, SortOrder order) {
        this(initCapacity, null, order);
    }

    public IntegerHeap(int initCapacity, IntComparator comparator, SortOrder order) {
        sifter = new Sifter(new int[initCapacity]).setSize(0).setComparator(comparator).setSortOrder(order);
    }

    @Override
    public int size() {
        return sifter.size;
    }

    @Override
    public Integer peek() {
        return sifter.size > 0 ? sifter.array[0] : null;
    }

    public int peekInt() {
        return sifter.peek();
    }

    @Override
    public Integer poll() {
        return sifter.size == 0 ? null : sifter.poll();
    }

    public int pollInt() {
        return sifter.poll();
    }

    @Override
    public boolean offer(Integer e) {
        return sifter.offer(e);
    }

    public boolean offer(int e) {
        // modCount++;
        return sifter.offer(e);
    }

    @Override
    public Integer pollAndOffer(Integer e) {
        return sifter.pollAndOffer(e);
    }

    public int pollAndOffer(int e) {
        // modCount++;
        return sifter.pollAndOffer(e);
    }

    public static class Sifter {
        private int[] array;
        private int size;
        private IntComparator comparator;
        private Ordering ordering = Ordering.NATURAL_ASC;

        public Sifter(final int[] array) {
            if (array == null) throw newArrayNotNullException();
            this.array = array;
            size = array.length;
        }

        private NullPointerException newArrayNotNullException() {
            return new NullPointerException("Argument 'array' must not be null");
        }

        public int[] getArray() {
            return array;
        }

        public Sifter setArray(int[] array) {
            if (array == null) throw newArrayNotNullException();
            this.array = array;
            if (size > array.length) size = array.length;
            return this;
        }

        public int getSize() {
            return size;
        }

        public Sifter setSize(int size) {
            this.size = array.length < size ? array.length : size;
            return this;
        }

        public SortOrder getSortOrder() {
            return ordering.isAscending() ? SortOrder.ASCENDING : SortOrder.DESCENDING;
        }

        public Sifter setSortOrder(SortOrder order) {
            setOrdering(order != SortOrder.DESCENDING);
            return this;
        }

        public IntComparator getComparator() {
            return comparator;
        }

        public Sifter setComparator(IntComparator comparator) {
            this.comparator = comparator;
            setOrdering(ordering.isAscending());
            return this;
        }

        private void setOrdering(boolean isAsc) {
            ordering = (comparator == null) ? (isAsc ? Ordering.NATURAL_ASC : Ordering.NATURAL_DESC)
                    : (isAsc ? Ordering.COMPARATOR_ASC : Ordering.COMPARATOR_DESC);
        }

        public void grow(int minCapacity) {
            array = Arrays.copyOf(array, ArrayUtils.newCapacity(array.length, minCapacity));
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
            if (i >= array.length) grow(i + 1);
            size = i + 1;
            if (i == 0) array[0] = e;
            else siftUp(i, e);
            return true;
        }

        public void sort() {
            final int originalSize = size;
            final Ordering originalOrdering = ordering;
            setOrdering(!originalOrdering.isAscending());
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

        public void siftUp(int index, final int value) {
            switch (ordering) {
            case NATURAL_ASC:
                siftUpNatural(index, value);
                return;
            case NATURAL_DESC:
                siftUpNaturalDesc(index, value);
                return;
            case COMPARATOR_ASC:
                siftUpComparator(index, value);
                return;
            case COMPARATOR_DESC:
                siftUpComparatorDesc(index, value);
                return;
            }
        }

        public void siftUpNatural(int index, final int value) {
            while (index > 0) {
                int parent = (index - 1) >>> 1;
                int e = array[parent];
                if (value >= e) break;
                array[index] = e;
                index = parent;
            }
            array[index] = value;
        }

        public void siftUpComparator(int index, final int value) {
            while (index > 0) {
                int parent = (index - 1) >>> 1;
                int e = array[parent];
                if (comparator.compare(value, e) >= 0) break;
                array[index] = e;
                index = parent;
            }
            array[index] = value;
        }

        public void siftUpNaturalDesc(int index, final int value) {
            while (index > 0) {
                int parent = (index - 1) >>> 1;
                int e = array[parent];
                if (value <= e) break;
                array[index] = e;
                index = parent;
            }
            array[index] = value;
        }

        public void siftUpComparatorDesc(int index, final int value) {
            while (index > 0) {
                int parent = (index - 1) >>> 1;
                int e = array[parent];
                if (comparator.compare(value, e) <= 0) break;
                array[index] = e;
                index = parent;
            }
            array[index] = value;
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

        public void siftDownNatural(int index, final int value) {
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

        public void siftDownComparator(int index, final int value) {
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

        public void siftDownNaturalDesc(int index, final int value) {
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

        public void siftDownComparatorDesc(int index, final int value) {
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
}
