package com.sharneng.algorithm;

import org.hamcrest.Description;

class DoubleHeapMatcher extends AbstractHeapMatcher {
    private final DoubleComparator comparator;
    private double[] array;

    public DoubleHeapMatcher(int size, final SortOrder sortOrder) {
        super(size, sortOrder == null ? SortOrder.ASCENDING : sortOrder);
        comparator = sortOrder == SortOrder.DESCENDING ? DoubleComparator.DESCENDING : DoubleComparator.ASCENDING;
    }

    public DoubleHeapMatcher(int size, DoubleComparator comparator) {
        super(size, null);
        this.comparator = comparator;

    }

    @Override
    protected boolean isInOrder(int front, int back) {
        return comparator.compare(array[front], array[back]) <= 0;
    }

    @Override
    protected int setArrayAndGetLength(Object item) {
        array = (double[]) item;
        return array.length;
    }

    @Override
    protected void descriptionElementType(Description description) {
        description.appendText("double");

    }

}