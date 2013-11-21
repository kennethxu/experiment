package com.sharneng.experiment.euler;

public class P1 {
	public static void main(String[] args) {
		System.out.println(arithmeticSeries(3, 1000) + arithmeticSeries(5, 1000) - arithmeticSeries(15, 1000));
	}
	
	private static int arithmeticSeries(int inc, final int limit)
	{
		int count = (limit-1) / inc;
		return inc * (1+count) * count / 2;
	}
}
