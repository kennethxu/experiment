package com.sharneng.experiment.sort;

public class BasicQuickSort extends AbstractQuickSort {
	public BasicQuickSort()
	{
	}
	
	public BasicQuickSort(Partitioner p)
	{
		super(p);
	}

	@Override
	public void sort(int[] a, int l, int r) {
		if (l>=r) return;
//		level++;
//		if (level>maxStack) maxStack = level;
		int m = partitioner.partition(a, l, r);
		sort(a, l, m);
		sort(a, m+1, r);
//		level--;
	}
}
