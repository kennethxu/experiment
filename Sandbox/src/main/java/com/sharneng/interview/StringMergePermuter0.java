package com.sharneng.interview;

import java.util.function.Consumer;

public class StringMergePermuter0 implements StringMergePermuter {
	
	private static void merge(Consumer<? super String> consumer, String builder, String... inputs) {
		boolean done = true;
		for (int i = inputs.length - 1; i >=0; i--) {
			String s = inputs[i];
			if (s.length()>0) {
				done = false;
				inputs[i] = s.substring(1);
				merge(consumer, builder + s.charAt(0), inputs);
				inputs[i] = s;
			}
		}
		if (done) consumer.accept(builder);
	}

	@Override
	public void merge(Consumer<? super String> consumer, String... inputs) {
		merge(consumer, "", inputs.clone());
	}
}
