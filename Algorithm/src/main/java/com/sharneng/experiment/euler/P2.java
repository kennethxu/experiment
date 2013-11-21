package com.sharneng.experiment.euler;

public class P2 {
	public static void main(String[] args) {
		System.out.println(fibonacciSeries(4000000));
	}
	
	private static int fibonacciSeries(final int limit)
	{
		int i = 1, j = 2, sum = 0;
		while (j <= limit)
		{
			sum += j;
			int x = i + j;
			i = j + x;
			j = x + i;
		}
		return sum;
	}

}
