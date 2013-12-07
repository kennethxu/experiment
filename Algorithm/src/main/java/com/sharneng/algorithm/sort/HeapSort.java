package com.sharneng.algorithm.sort;

import com.sharneng.algorithm.heap.IntHeap;

public class HeapSort implements Sorter {

    @Override
    public void sort(int[] a) {
        new IntHeap(a).setSize(a.length).sort();
    }

}
