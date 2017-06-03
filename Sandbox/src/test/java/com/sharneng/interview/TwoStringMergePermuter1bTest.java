package com.sharneng.interview;

import java.util.Collection;

public class TwoStringMergePermuter1bTest extends StringMergePermuterTest {

	@Override
	protected Collection<String> merge(String[] inputs) {
		return new TwoStringMergePermuter1b().merge(inputs[0], inputs[1]);
	}

}
