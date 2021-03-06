package com.sharneng.algorithm;

import javax.annotation.CheckForNull;

import org.hamcrest.Description;

class SortedDoubleArrayMatcher extends AbstractSortedArrayMatcher<double[]> {
    private final DoubleComparator comparator;
    @CheckForNull
    private double[] array;

    public SortedDoubleArrayMatcher(int size, final Scending scending) {
        super(size, scending == null ? Scending.ASCENDING : scending);
        comparator = scending == Scending.DESCENDING ? DoubleComparator.DESCENDING : DoubleComparator.ASCENDING;
    }

    public SortedDoubleArrayMatcher(int size, DoubleComparator comparator) {
        super(size, null);
        this.comparator = comparator;

    }

    @Override
    protected boolean isInOrder(int front, int back) {
    	assert(array != null);
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