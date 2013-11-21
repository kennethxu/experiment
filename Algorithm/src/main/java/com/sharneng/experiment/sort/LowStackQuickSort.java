package com.sharneng.experiment.sort;

public class LowStackQuickSort extends AbstractQuickSort {
	public LowStackQuickSort()
	{
		super();
	}
	
	public LowStackQuickSort(Partitioner p)
	{
		super(p);
	}
	
	@Override
	public void sort(int[] a, int l, int r) {
//		level++;
//		if (level>maxStack) maxStack = level;
		while (l<r) {
			int p = partitioner.partition(a, l, r);
			if (p<=(r+l)/2) {
				sort(a, l, p);
				l = p + 1;
			}else{
				sort(a, p+1, r);
				r = p;
			}
		}
//		level--;
	}

}
