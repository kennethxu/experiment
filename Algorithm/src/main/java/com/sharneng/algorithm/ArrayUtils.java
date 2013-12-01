package com.sharneng.algorithm;

import java.util.Arrays;
import java.util.Random;

public class ArrayUtils {

    public static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    public static void print(String comment, int[] array) {
        System.out.printf("%10s: %s\n", comment, Arrays.toString(array));
    }

    public static int[] orderedArray(int n) {
        int[] a = new int[n];
        for (int i = n - 1; i >= 0; i--)
            a[i] = i;
        return a;
    }

    public static int[] randomArray(int n) {
        int[] a = new int[n];
        Random r = new Random();
        for (int i = n - 1; i >= 0; i--) {
            a[i] = Math.abs(r.nextInt());
        }
        return a;

    }

    public static int[] randomArray(int n, int max) {
        int[] a = new int[n];
        Random r = new Random();
        for (int i = n - 1; i >= 0; i--) {
            a[i] = r.nextInt(max);
        }
        return a;

    }

    public static int[] sameArray(int n, int value) {
        int[] a = new int[n];
        Arrays.fill(a, value);
        return a;
    }

    public static int[] clone(int[] a) {
        return a.clone();
    }

    public static void AssertEquals(int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            ArrayUtils.print("Expected", expected);
            ArrayUtils.print("Actual", actual);
            throw new RuntimeException("Array is not in order.");
        }
    }

    public static void copy(int[] source, int from, int to, int[] target, int index) {
        for (int i = from; i < to; i++, index++)
            target[index] = source[i];
    }

    /**
     * Helper method to compute the optimal new array size to accommodate the required capacity. This is typically used
     * to compute next array size of array based unbounded collection data structure.
     * 
     * @param oldCapacity
     *            current capacity
     * @param minCapacity
     *            the required capacity
     * @return the suggested new capacity
     */
    public static int newCapacity(int oldCapacity, int minCapacity) {
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1));
        // overflow-conscious code
        if (newCapacity - ArrayUtils.MAX_ARRAY_SIZE > 0) {
            if (minCapacity < 0) // overflow
            throw new OutOfMemoryError("exceeded array size limit");
            newCapacity = (minCapacity > ArrayUtils.MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : ArrayUtils.MAX_ARRAY_SIZE;
        }
        return newCapacity;
    }

    /**
     * The maximum size of array to allocate. Some VMs reserve some header words in an array. Attempts to allocate
     * larger arrays may result in OutOfMemoryError: Requested array size exceeds VM limit
     */
    public static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
}
