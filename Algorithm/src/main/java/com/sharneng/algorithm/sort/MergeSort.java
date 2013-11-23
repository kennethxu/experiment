package com.sharneng.algorithm.sort;
import com.sharneng.algorithm.ArrayUtils;

import java.util.Arrays;


public class MergeSort implements Sorter {

	@Override
	public void sort(int[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(int[] a, int l, int r) {
		if (r-l>1) {
			int m = (l+r)/2;
			sort(a, l, m);
			sort(a, m, r);
			merge(a, l, m, r);
		}
	}
	
	private static void merge(int[] a, int l, int m, int r) {
		int i = l;
		
		int[] a1 = Arrays.copyOfRange(a, l, m);
		int end1 = a1.length;
		int i1 = 0; 
		
		int[] a2 = a;
		int end2 = r;
		int i2 = m;
		
		while(i1<end1 && i2<end2) {
			a[i++] = (a1[i1]<=a2[i2]) ? a1[i1++] : a2[i2++];
		}
		if (i1<end1) ArrayUtils.copy(a1, i1, end1, a, i);
	}

}
