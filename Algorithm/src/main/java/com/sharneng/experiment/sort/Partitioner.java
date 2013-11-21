package com.sharneng.experiment.sort;
public class Partitioner {
	public int partition(int[] a, int l, int r) {
		return partition(a, l, r, a[l]);
	}
	
	static int partition(int[] a, int l, int r, int x) {
		--l; ++r;
		while(true) {
			do --r; while(a[r] > x);
			do ++l; while(a[l] < x);
			if (l>=r) return r;
			ArrayUtils.swap(a, l, r);
		}
	}
}
