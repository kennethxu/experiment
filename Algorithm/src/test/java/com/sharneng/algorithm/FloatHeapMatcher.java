package com.sharneng.algorithm;

import org.hamcrest.Description;

class FloatHeapMatcher extends AbstractHeapMatcher {
    private final FloatComparator comparator;
    private float[] array;

    public FloatHeapMatcher(int size, final SortOrder sortOrder) {
        super(size, sortOrder == null ? SortOrder.ASCENDING : sortOrder);
        comparator = sortOrder == SortOrder.DESCENDING ? FloatComparator.DESCENDING : FloatComparator.ASCENDING;
    }

    public FloatHeapMatcher(int size, FloatComparator comparator) {
        super(size, null);
        this.comparator = comparator;

    }

    @Override
    protected boolean isInOrder(int front, int back) {
        return comparator.compare(array[front], array[back]) <= 0;
    }

    @Override
    protected int setArrayAndGetLength(Object item) {
        array = (float[]) item;
        return array.length;
    }

    @Override
    protected void descriptionElementType(Description description) {
        description.appendText("float");
    }

}