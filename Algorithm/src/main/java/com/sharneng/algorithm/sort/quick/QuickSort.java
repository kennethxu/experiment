package com.sharneng.algorithm.sort.quick;
@Favorites(value = {NullPointerException.class, NullPointerException.class}, value1="string")
public class QuickSort {
	static void stackFriendlySort(int[] a) {
		stackFriendlySort(a, 0, a.length-1);
	}
	static void sort(int[] a) {
		sort(a, 0, a.length-1);
	}
	
	static void stackFriendlySort(int[] a, int l, int r) {
		while (l<r) {
			int m = r-l/2;
			int p = partition(a, l, r);
			if (p<=m) {
				sort(a, l, p);
				l = p + 1;
			}else{
				sort(a, p+1, r);
				r = p;
			}
		}
	}
	
	static void sort(int[] a, int l, int r) {
		if (l>=r) return;
		int m = partition(a, l, r);
		sort(a, l, m);
		sort(a, m+1, r);
	}
	
	static int partition(int[] a, int l, int r) {
		int x = a[l];
		--l; ++r;
		while(true) {
			do --r; while(a[r] > x);
			do ++l; while(a[l] < x);
			if (l>=r) return r;
			ArrayUtils.swap(a, l, r);
		}
	}
}
