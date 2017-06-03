package com.sharneng.interview;

import java.util.Collection;

public class StringMergePermuter1Test extends StringMergePermuterTest {

	@Override
	protected Collection<String> merge(String[] inputs) {
		return new StringMergePermuter1().merge(inputs);
	}

}
