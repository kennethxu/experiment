package com.sharneng.experiment.sort;

public class RadixSort implements Sorter {

	@Override
	public void sort(int[] a) {
		ByteSorter s = new ByteSorter();
		s.setBytePosition(0);
		int[] a2 = s.sort(a, null);
		s.setBytePosition(1);
		s.sort(a2, a);
		s.setBytePosition(2);
		s.sort(a, a2);
		s.setBytePosition(3);
		s.sort(a2, a);
	}

	private static class ByteSorter extends CountingSort
	{
		private int shift;
		private int mask;
		
		ByteSorter()
		{
			super(256);
		}
		
		void setBytePosition(int b)
		{
			this.shift = b*8;
			mask = 255 << shift;
		}
		
		@Override
		protected int position(int x)
		{
			return (x & mask) >>> shift;
		}
	}
}
