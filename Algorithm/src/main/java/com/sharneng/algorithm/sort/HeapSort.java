package com.sharneng.algorithm.sort;

public class HeapSort implements Sorter {

	@Override
	public void sort(int[] a) {
		Heap.heapsort(a);
	}

}
