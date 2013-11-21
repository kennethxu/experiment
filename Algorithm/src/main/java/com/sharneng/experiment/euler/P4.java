package com.sharneng.experiment.euler;
import java.util.Iterator;


public class P4 {

	private static class PalindromicGenerator implements Iterator<Integer> {
		int i = 999;

		@Override
		public boolean hasNext() {
			return i >= 100;
		}

		@Override
		public Integer next() {
			Integer x = i * 1000 + i%10 * 100 + i%100/10*10 + i/100;
			i--;
			return x;
		}

		@Override
		public void remove() {
		}
	}
	
	public static void main(String[] args)
	{
		PalindromicGenerator g = new PalindromicGenerator();
		while(g.hasNext()) {
			int next = g.next();
			if (verify(next))
			{
				System.out.println(next);
				break;
			}
		}
	}

	private static boolean verify(int next) {
		for (int x = (int) Math.sqrt(next); x > 100; x--)
		{
			int y = next / x;
			if (y > 999) return false;
			if (y * x == next) return true;
		}
		return false;
	}
}
