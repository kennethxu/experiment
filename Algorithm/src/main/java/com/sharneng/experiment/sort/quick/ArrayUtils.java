package com.sharneng.experiment.sort.quick;
import java.util.Arrays;


public class ArrayUtils {

	static void swap(int[] array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}

	static void print(String comment, int[] array) {
		System.out.printf("%10s: %s\n", comment, Arrays.toString(array));
	}
}
