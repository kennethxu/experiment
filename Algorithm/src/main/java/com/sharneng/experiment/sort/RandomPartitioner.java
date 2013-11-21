package com.sharneng.experiment.sort;
import java.util.Random;


public class RandomPartitioner extends Partitioner {
	private Random random = new Random();
	
	public int partition(int[] a, int l, int r)
	{
		int i = random.nextInt(r - l + 1) + l;
		return partition(a, l, r, a[i]);
	}
}
