package com.sharneng.algorithm;

import org.hamcrest.Description;

public abstract class AbstractHeapMatcher extends AbstractArrayMatcher {

    protected AbstractHeapMatcher(final int size, final SortOrder sortOrder) {
        super(size, sortOrder);
    }

    @Override
    public boolean matches(Object item) {
        final int length = setArrayAndGetLength(item);
        final int s = actualSize = (size < 0 || size > length) ? length : size;
        int half = s >>> 1;
        for (int i = 0; i < half; i++) {
            int l = (i << 1) + 1;
            int r = l + 1;
            if (isInOrder(i, l) && (r >= s || isInOrder(i, r))) continue;
            failedAt = i;
            return false;
        }
        return true;
    }

    @Override
    protected void descriptionStructure(Description description) {
        description.appendText("heapified");
    }

}