package com.sharneng.algorithm;

import javax.annotation.CheckForNull;

import org.hamcrest.Matcher;

public class Matchers extends org.hamcrest.Matchers {
    public static <E> Matcher<E[]> isHeap() {
        return null;
    }

    public static <A> Matcher<A> isHeap(Class<A> type) {
        return isHeap(type, -1, null);
    }

    public static <A> Matcher<A> isHeap(Class<A> type, final int size) {
        return isHeap(type, size, null);
    }

    public static <A> Matcher<A> isHeap(Class<A> type, final Scending scending) {
        return isHeap(type, -1, scending);
    }

    @SuppressWarnings("unchecked")
    public static <A> Matcher<A> isHeap(final Class<A> type, final int size, @CheckForNull final Scending scending) {
        if (!type.isArray()) throw new IllegalArgumentException("type must be an array but got " + type);
        if (type == int[].class) return (Matcher<A>) new IntHeapMatcher(size, scending);
        if (type == long[].class) return (Matcher<A>) new LongHeapMatcher(size, scending);
        if (type == float[].class) return (Matcher<A>) new FloatHeapMatcher(size, scending);
        if (type == double[].class) return (Matcher<A>) new DoubleHeapMatcher(size, scending);
        return (Matcher<A>) isHeap();
    }

    public static Matcher<int[]> isHeap(final IntComparator comparator) {
        return isHeap(-1, comparator);
    }

    public static Matcher<int[]> isHeap(final int size, final IntComparator comparator) {
        return new IntHeapMatcher(size, comparator);
    }

    public static Matcher<long[]> isHeap(final LongComparator comparator) {
        return isHeap(-1, comparator);
    }

    public static Matcher<long[]> isHeap(final int size, final LongComparator comparator) {
        return new LongHeapMatcher(size, comparator);
    }

    public static Matcher<float[]> isHeap(final FloatComparator comparator) {
        return isHeap(-1, comparator);
    }

    public static Matcher<float[]> isHeap(final int size, final FloatComparator comparator) {
        return new FloatHeapMatcher(size, comparator);
    }

    public static Matcher<double[]> isHeap(final DoubleComparator comparator) {
        return isHeap(-1, comparator);
    }

    public static Matcher<double[]> isHeap(final int size, final DoubleComparator comparator) {
        return new DoubleHeapMatcher(size, comparator);
    }

    public static <E> Matcher<E[]> isSorted() {
        return null;
    }

    public static <A> Matcher<A> isSorted(Class<A> type) {
        return isSorted(type, -1, null);
    }

    public static <A> Matcher<A> isSorted(Class<A> type, final int size) {
        return isSorted(type, size, null);
    }

    public static <A> Matcher<A> isSorted(Class<A> type, final Scending scending) {
        return isSorted(type, -1, scending);
    }

    @SuppressWarnings("unchecked")
    public static <A> Matcher<A> isSorted(final Class<A> type, final int size, @CheckForNull final Scending order) {
        if (!type.isArray()) throw new IllegalArgumentException("type must be an array but got " + type);
        if (type == int[].class) return (Matcher<A>) new SortedIntArrayMatcher(size, order);
        if (type == long[].class) return (Matcher<A>) new SortedLongArrayMatcher(size, order);
        if (type == float[].class) return (Matcher<A>) new SortedFloatArrayMatcher(size, order);
        if (type == double[].class) return (Matcher<A>) new SortedDoubleArrayMatcher(size, order);
        return (Matcher<A>) isSorted();
    }

    public static Matcher<int[]> isSorted(final IntComparator comparator) {
        return isSorted(-1, comparator);
    }

    public static Matcher<int[]> isSorted(final int size, final IntComparator comparator) {
        return new SortedIntArrayMatcher(size, comparator);
    }

    public static Matcher<long[]> isSorted(final LongComparator comparator) {
        return isSorted(-1, comparator);
    }

    public static Matcher<long[]> isSorted(final int size, final LongComparator comparator) {
        return new SortedLongArrayMatcher(size, comparator);
    }

    public static Matcher<float[]> isSorted(final FloatComparator comparator) {
        return isSorted(-1, comparator);
    }

    public static Matcher<float[]> isSorted(final int size, final FloatComparator comparator) {
        return new SortedFloatArrayMatcher(size, comparator);
    }

    public static Matcher<double[]> isSorted(final DoubleComparator comparator) {
        return isSorted(-1, comparator);
    }

    public static Matcher<double[]> isSorted(final int size, final DoubleComparator comparator) {
        return new SortedDoubleArrayMatcher(size, comparator);
    }

}
