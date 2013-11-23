package com.sharneng.algorithm.sort;
public abstract class AbstractQuickSort implements Sorter {

	protected final Partitioner partitioner;
	protected int maxStack;
	protected int level;

	public AbstractQuickSort() {
		this(new Partitioner());
	}
	
	public AbstractQuickSort(Partitioner p)
	{
		partitioner = p;
	}

	@Override
	public void sort(int[] a) {
		sort(a, 0, a.length-1);
//		System.out.println("Max Stack: " + maxStack);
	}

	public abstract void sort(int[] a, int l, int r);
	
	@Override
	public String toString() {
		return this.getClass().getName() + '+' + partitioner.getClass().getName() + ':';
	}
}