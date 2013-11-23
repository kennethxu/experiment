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
}
