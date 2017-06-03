package com.sharneng.interview;

import java.util.function.Consumer;

public class StringMergePermuter1 implements StringMergePermuter {
	@Override
	public void merge(final Consumer<? super String> consumer, final String... inputs) {
		new Object() {
			private StringBuilder builder = new StringBuilder();
			final int[] index = new int[inputs.length];
			private void merge() {
				boolean done = true;
				int size = builder.length();
				for(int i = index.length-1; i >= 0; i--) {
					if (index[i] == inputs[i].length()) continue;
					done = false;
					builder.setLength(size);
					builder.append(inputs[i].charAt(index[i]));
					index[i]++;
					merge();
					index[i]--;
				}
				if (done) {
					consumer.accept(builder.toString());
				}
			}	
		}.merge();
	}
}
