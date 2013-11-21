package com.sharneng.experiment.sort;

public class InPlaceCountingSort extends AbstractCountingSort {

	private boolean isDebug;
	InPlaceCountingSort(int range)
	{
		super(range);
	}
	
	@Override
	public void sort(int[] a) {
		isDebug = (range <= 20 && a.length <= 20);
		count(a);
		int[] c = counter.clone();
		int i = a.length-1;
		while(true)
		{
			int value = a[i];
			debug(i, value, c, a);
			int position = position(value);
			int current = c[position];
			if (current == i) {
				c[position] = current - 1;
				if (--i<0) break;
			} else if (current < i && counter[position] >= i) {
				if (--i<0) break;
			} else {
				a[i] = a[current];
				a[current] = value;
				c[position] = current - 1;
			}
		}
//		for(int i = a.length-1; i>=0; i--)
//		{
//			int value = a[i];
//			debug(i, value, c, a);
//			int position = position(value);
//			int current = c[position];
//			if (current == i) {
//				c[position] = current - 1;
//				continue;
//			}
//			if (current < i && counter[position] >= i)
//			{
//				continue;
//			}
//			
//			a[i] = a[current];
//			a[current] = value;
//			c[position] = current - 1;
//			i++;
//		}
	}

	private void debug(int i, int value, int[] c, int[] a)
	{
		if (isDebug)
		{
			String comment = "i,value=" + i + "," + value;
			ArrayUtils.print(comment, a);
			ArrayUtils.print("Counter", c);
		}
	}
}
