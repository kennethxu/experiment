package com.sharneng.algorithm;

import org.hamcrest.Description;

class IntHeapMatcher extends AbstractHeapMatcher {
    private final IntComparator comparator;
    private int[] array;

    public IntHeapMatcher(int size, final Scending scending) {
        super(size, scending == null ? Scending.ASCENDING : scending);
        comparator = scending == Scending.DESCENDING ? IntComparator.DESCENDING : IntComparator.ASCENDING;
    }

    public IntHeapMatcher(int size, IntComparator comparator) {
        super(size, null);
        this.comparator = comparator;

    }

    @Override
    protected boolean isInOrder(int front, int back) {
        return comparator.compare(array[front], array[back]) <= 0;
    }

    @Override
    protected int setArrayAndGetLength(Object item) {
        array = (int[]) item;
        return array.length;
    }

    @Override
    protected void descriptionElementType(Description description) {
        description.appendText("int");
    }

}