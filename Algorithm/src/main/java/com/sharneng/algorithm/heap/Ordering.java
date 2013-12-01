package com.sharneng.algorithm.heap;

enum Ordering {
    NATURAL_ASC(false, true),
    NATURAL_DESC(false, false),
    COMPARATOR_ASC(true, true),
    COMPARATOR_DESC(true, false);

    private final boolean isComparator;
    private final boolean isAscending;

    private Ordering(boolean isComparator, boolean isAscending) {
        this.isComparator = isComparator;
        this.isAscending = isAscending;
    }

    boolean isAscending() {
        return isAscending;
    }

    boolean isComparator() {
        return isComparator;
    }
}