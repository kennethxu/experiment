package com.sharneng.algorithm.sort;

public class CountingSort extends AbstractCountingSort {
	public CountingSort(int range)
	{
		super(range);
	}
	
	@Override
	public void sort(int[] a) {
		int[] result = sort(a, null);
		for(int i = a.length-1; i>=0; i--)
		{
			a[i] = result[i];
		}
	}

	public int[] sort(int[] a, int[] result)
	{
		count(a);
		if(result==null) result = new int[a.length];
		for(int i = a.length-1; i>=0; i--)
		{
			int value = a[i];
			int position = position(value);
			int index = counter[position];
			result[index] = value;
			counter[position] = index-1;
		}
		return result;
	}
}
