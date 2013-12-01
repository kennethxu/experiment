package com.sharneng.algorithm;

import org.hamcrest.Description;

public abstract class AbstractSortedArrayMatcher extends AbstractArrayMatcher {

    protected AbstractSortedArrayMatcher(final int size, final SortOrder sortOrder) {
        super(size, sortOrder);
    }

    @Override
    public boolean matches(Object item) {
        final int length = setArrayAndGetLength(item);
        final int s = actualSize = (size < 0 || size > length) ? length : size;
        for (int i = 0; i < s - 1; i++) {
            if (isInOrder(i, i + 1)) continue;
            failedAt = i + 1;
            return false;
        }
        return true;
    }

    @Override
    protected void descriptionStructure(Description description) {
        description.appendText("sorted");
    }

}