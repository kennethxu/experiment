package com.sharneng.algorithm.sort;

import com.sharneng.algorithm.heap.Heap;

public class HeapSort implements Sorter {

	@Override
	public void sort(int[] a) {
		Heap.heapsort(a);
	}

}
