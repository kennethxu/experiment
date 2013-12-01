package com.sharneng.algorithm;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public abstract class AbstractArrayMatcher extends BaseMatcher<int[]> {

    protected final SortOrder sortOrder;
    protected final int size;
    protected int failedAt;
    protected int actualSize;

    protected AbstractArrayMatcher(int size, SortOrder sortOrder) {
        this.sortOrder = sortOrder;
        this.size = size;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("an array of ");
        descriptionElementType(description);
        if (size < 0) description.appendText(" that is ");
        else description.appendText(" with its first ").appendText(Integer.toString(actualSize))
                .appendText(" elements ");
        descriptionStructure(description);
        description.appendText(" heapified in the order of ");
        switch (sortOrder) {
        case ASCENDING:
            description.appendText("ascending");
            break;
        case DESCENDING:
            description.appendText("descending");
            break;
        default:
            description.appendText("comparator");
            break;
        }
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        super.describeMismatch(item, description);
        description.appendText(" where not heapified at position " + failedAt);
    }

    protected abstract int setArrayAndGetLength(Object item);

    protected abstract boolean isInOrder(int front, int back);

    protected abstract void descriptionElementType(Description description);

    protected abstract void descriptionStructure(Description description);

}