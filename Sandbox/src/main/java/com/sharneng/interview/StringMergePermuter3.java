package com.sharneng.interview;

import java.util.function.Consumer;

public class StringMergePermuter3 implements StringMergePermuter {
	@Override
	public void merge(final Consumer<? super String> consumer, final String... inputs) {
		new Object() {
			private StringBuilder builder = new StringBuilder();
			final int[] index = new int[inputs.length];
			private void merge() {
				boolean done = true;
				int last = -1;
				int length = builder.length();
				for (int i = index.length-1; i >= 0 ; i--) {
					if (index[i] == inputs[i].length()) continue;
					if (last == -1) last = i;
					else {
						if (done) {
							done = false;
							goNextStep(last, length);
						}
						goNextStep(i, length);;
					}
				}
				if (done) {
					builder.append(inputs[last], index[last], inputs[last].length());
					consumer.accept(builder.toString());
					builder.setLength(length);
				}
			}

			private void goNextStep(int last, int length) {
				builder.setLength(length);
				builder.append(inputs[last].charAt(index[last]));
				index[last]++;
				merge();
				index[last]--;
			}	
		}.merge();
	}
}
