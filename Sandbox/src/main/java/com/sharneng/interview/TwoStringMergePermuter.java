package com.sharneng.interview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public interface TwoStringMergePermuter {
	public void merge(Consumer<? super String> consumer, String s1, String s2);
	default public Collection<String> merge(String s1, String s2) {
		List<String> bag = new ArrayList<>();
		merge(x->bag.add(x), s1, s2);
		return bag;
	}
}
