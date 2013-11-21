package com.sharneng.experiment.sort;

public class HeapSort implements Sorter {

	@Override
	public void sort(int[] a) {
		Heap.heapsort(a);
	}

}
