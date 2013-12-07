package com.sharneng.algorithm;

import org.hamcrest.Description;

class SortedLongArrayMatcher extends AbstractSortedArrayMatcher {
    private final LongComparator comparator;
    private long[] array;

    public SortedLongArrayMatcher(int size, final Scending sortOrder) {
        super(size, sortOrder == null ? Scending.ASCENDING : sortOrder);
        comparator = sortOrder == Scending.DESCENDING ? LongComparator.DESCENDING : LongComparator.ASCENDING;
    }

    public SortedLongArrayMatcher(int size, LongComparator comparator) {
        super(size, null);
        this.comparator = comparator;

    }

    @Override
    protected boolean isInOrder(int front, int back) {
        return comparator.compare(array[front], array[back]) <= 0;
    }

    @Override
    protected int setArrayAndGetLength(Object item) {
        array = (long[]) item;
        return array.length;
    }

    @Override
    protected void descriptionElementType(Description description) {
        description.appendText("long");
    }

}