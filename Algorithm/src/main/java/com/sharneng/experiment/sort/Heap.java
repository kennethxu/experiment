package com.sharneng.experiment.sort;

public class Heap {

	static void heapify(int[] array, int length, int i) {
		int l = left(i);
		int r = right(i);
		int largest = (l < length && array[l] > array[i]) ? l : i;
		if (r < length && array[r] > array[largest]) largest = r;
		if (largest != i) {
			ArrayUtils.swap(array, i, largest);
			heapify(array, length, largest);
		}
	}
	
	static void heapsort(int[] array) {
		buildHeap(array);
		for(int i = array.length-1; i>0; i--) {
			ArrayUtils.swap(array, 0, i);
			Heap.heapify(array, i, 0 );
//			ArrayUtils.print("Sort " + i, array);
		}
	}

	static void buildHeap(int[] array) {
		int length = array.length;
		for(int i = length/2-1; i >= 0; i--) {
			Heap.heapify(array, length, i);
//			ArrayUtils.print("Build " + i, array);
		}
	}

	static int right(int i) {
		return i*2+2;
	}

	static int left(int i) {
		return i*2+1;
	}
}
