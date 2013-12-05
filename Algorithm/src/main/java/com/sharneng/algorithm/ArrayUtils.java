package com.sharneng.algorithm;

import java.util.Arrays;

/**
 * Utility class contains common methods dealing with array.
 * 
 * @author Kenneth Xu
 * 
 */
public final class ArrayUtils {
    private ArrayUtils() {
    }

    /**
     * Swap two elements in an array.
     * 
     * @param array
     *            array containing the elements to be swapped
     * @param x
     *            the index of the first element to be swapped
     * @param y
     *            the index of the second element to be swapped
     */
    public static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    /**
     * Print the array with a comment. This is debug helper, need refactoring.
     * 
     * @param comment
     *            comment to print before array
     * @param array
     *            the arrya to print
     */
    public static void print(String comment, int[] array) {
        System.out.printf("%10s: %s%n", comment, Arrays.toString(array));
    }

    /**
     * Copies array elements from one to another.
     * 
     * @param source
     *            the source array containing the elements to be copied.
     * @param from
     *            the starting (inclusive) index of the element in source array to be copied
     * @param to
     *            the ending (exclusive) index of the element in the source array to be copied
     * @param target
     *            the target array to copy the elements to
     * @param index
     *            the starting index in the target array to copy the elements
     */
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
        // SUPPRESS CHECKSTYLE MagicNumber BECAUSE 64 is an experienced number.
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
    // SUPPRESS CHECKSTYLE MagicNumber BECAUSE 8 is JVM overhead
    public static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
}
