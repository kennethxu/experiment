package com.sharneng.experiment.sort;
import java.util.Arrays;


public class JdkSort implements Sorter {

	@Override
	public void sort(int[] a) {
		Arrays.sort(a);
	}

}
