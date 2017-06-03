package com.sharneng.interview;

import java.util.function.Consumer;

public class TwoStringMergePermuter1b implements TwoStringMergePermuter {
	public void merge(final Consumer<? super String> consumer, final String s1, final String s2) {
		new Object() {
			private StringBuilder builder = new StringBuilder();
			private void merge(final int i, final int j) {
				boolean done = true;
				if (i < s1.length()) {
					done = false;
					builder.setLength(i + j);
					builder.append(s1.charAt(i));
					merge(i+1, j);
				}
				if (j < s2.length()) {
					done = false;
					builder.setLength(i + j);
					builder.append(s2.charAt(j));
					merge(i, j+1);
				}
				if (done) {
					consumer.accept(builder.toString());
				}
			}	
		}.merge(0, 0);
	}
}
