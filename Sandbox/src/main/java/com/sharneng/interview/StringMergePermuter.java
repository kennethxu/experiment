package com.sharneng.interview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public interface StringMergePermuter {
	public void merge(Consumer<? super String> consumer, String... inputs);
	
	default public Collection<String> merge(String... inputs) {
		List<String> bag = new ArrayList<>();
		merge(x->bag.add(x), inputs.clone());
		return bag;
	}
}
