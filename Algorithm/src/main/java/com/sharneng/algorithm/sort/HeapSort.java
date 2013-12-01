package com.sharneng.algorithm.sort;

import com.sharneng.algorithm.heap.IntegerHeap;

public class HeapSort implements Sorter {

    @Override
    public void sort(int[] a) {
        new IntegerHeap.Sifter(a).sort();
    }

}
