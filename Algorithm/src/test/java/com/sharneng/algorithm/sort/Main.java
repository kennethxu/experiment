package com.sharneng.algorithm.sort;

import com.sharneng.algorithm.ArrayTestUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    private static final int range = 1024;
    private static final int n = 1000000;
    private static final int[] source = ArrayTestUtils.randomArray(n);
    private static int[] expected;

    public static void main(String[] args) {
        expected = ArrayTestUtils.clone(source);
        Arrays.sort(expected);

        // int[] array = new int[]{
        // 4, 1, 3, 2, 16, 9, 10, 14, 8, 7,
        // //1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        // //1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        // };

        // runSort(new LowStackQuickSort(new RandomPartitioner()));
        // runSort(new LowStackQuickSort());
        // runSort(new BasicQuickSort(new RandomPartitioner()));
        // runSort(new BasicQuickSort());
        // runSort(new HeapSort());
        // runSort(new CountingSort(range));
        // runSort(new InPlaceCountingSort(range));
        runSort(new RadixSort());
        runSort(new RadixSort());
        runSort(new MergeSort());
        runSort(new JdkSort());
        runSort(new JdkSort());
    }

    private static void runSort(Sorter sorter) {
        int[] array = null;
        try {
            array = ArrayTestUtils.clone(source);
            long t = System.nanoTime();
            sorter.sort(array);
            System.out.printf("%,12d %s\n", System.nanoTime() - t, sorter);
            ArrayTestUtils.AssertEquals(expected, array);
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }

    public static <T extends Comparable<? super T>> T max(List<? extends T> list) {
        Iterator<? extends T> i = list.iterator();
        T result = i.next();
        while (i.hasNext()) {
            T t = i.next();
            if (t.compareTo(result) > 0) result = t;
        }
        return result;
    }

    public static void swap(List<?> list, int i, int j) {
        swapprivate(list, i, j);
    }

    private static <E> void swapprivate(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }
}
