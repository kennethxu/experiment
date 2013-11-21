package com.sharneng.experiment.sort;
import java.util.Arrays;

public abstract class AbstractCountingSort implements Sorter {

	protected final int range;
	protected final int[] counter;

	protected AbstractCountingSort(int range)
	{
		this.range = range;
		counter = new int[range];
	}

	protected void count(int[] a) {
		Arrays.fill(counter, 0);
		for(int i = a.length-1; i>=0; i--)
		{
			counter[position(a[i])] ++;
		}
		for(int sum = -1, end = range, i = 0; i<end; i++)
		{
			int count = counter[i];
			counter[i] = sum + count;
			sum += count;
		}
	}

	protected int position(int x) {
		return x;
	}
}