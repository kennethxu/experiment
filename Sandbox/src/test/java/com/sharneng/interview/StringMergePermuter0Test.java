package com.sharneng.interview;

import java.util.Collection;

public class StringMergePermuter0Test extends StringMergePermuterTest {

	@Override
	protected Collection<String> merge(String[] inputs) {
		return new StringMergePermuter0().merge(inputs);
	}

}
