package com.sharneng.interview;

import java.util.function.Consumer;

public class TwoStringMergePermuter1a implements TwoStringMergePermuter {
	public void merge(Consumer<? super String> consumer, String s1, String s2) {
		merge("", s1, s2, consumer);
	}
	
	private static void merge(String prefix, String s1, String s2, Consumer<? super String> bag) {
		if (s1.length()>0) merge(prefix + s1.charAt(0), s1.substring(1), s2, bag);
		if (s2.length()>0) merge(prefix + s2.charAt(0), s1, s2.substring(1), bag);
		if (s1.length()==0 && s2.length()==0) bag.accept(prefix);
	}
}
