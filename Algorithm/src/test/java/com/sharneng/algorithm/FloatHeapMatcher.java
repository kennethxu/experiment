package com.sharneng.algorithm;

import javax.annotation.CheckForNull;

import org.hamcrest.Description;

class FloatHeapMatcher extends AbstractHeapMatcher<float[]> {
    private final FloatComparator comparator;
    @CheckForNull
    private float[] array;

    public FloatHeapMatcher(int size, final Scending scending) {
        super(size, scending == null ? Scending.ASCENDING : scending);
        comparator = scending == Scending.DESCENDING ? FloatComparator.DESCENDING : FloatComparator.ASCENDING;
    }

    public FloatHeapMatcher(int size, FloatComparator comparator) {
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
        array = (float[]) item;
        return array.length;
    }

    @Override
    protected void descriptionElementType(Description description) {
        description.appendText("float");
    }

}