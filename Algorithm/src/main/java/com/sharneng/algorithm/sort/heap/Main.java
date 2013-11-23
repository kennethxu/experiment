package com.sharneng.algorithm.sort.heap;
public class Main {
	public static void main(String[] args)
	{
		int[] array = new int[]{
			4, 1, 3, 2, 16, 9, 10, 14, 8, 7
		};

		ArrayUtils.print("Init", array);
		Heap.heapsort(array);
		ArrayUtils.print("Result", array);
	}
}
