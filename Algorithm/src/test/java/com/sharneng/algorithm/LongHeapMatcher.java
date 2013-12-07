package com.sharneng.algorithm;

import org.hamcrest.Description;

class LongHeapMatcher extends AbstractHeapMatcher {
    private final LongComparator comparator;
    private long[] array;

    public LongHeapMatcher(int size, final Scending scending) {
        super(size, scending == null ? Scending.ASCENDING : scending);
        comparator = scending == Scending.DESCENDING ? LongComparator.DESCENDING : LongComparator.ASCENDING;
    }

    public LongHeapMatcher(int size, LongComparator comparator) {
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