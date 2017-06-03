package com.sharneng.interview;

import java.util.function.Consumer;

public class TwoStringMergePermuter2b implements TwoStringMergePermuter {
	public void merge(final Consumer<? super String> consumer, final String s1, final String s2) {
		new Object() {
			private StringBuilder builder = new StringBuilder();
			private void merge(final int i, final int j) {
				builder.setLength(i + j);
				if (i == s1.length()) {
					builder.append(s2, j, s2.length());
					consumer.accept(builder.toString());
				} else if (j == s2.length()) {
					builder.append(s1, i, s1.length());
					consumer.accept(builder.toString());
				} else {
					builder.append(s1.charAt(i));
					merge(i+1, j);
					builder.setCharAt(i+j, s2.charAt(j));
					merge(i, j+1);
				}
			}	
		}.merge(0, 0);
	}
}
