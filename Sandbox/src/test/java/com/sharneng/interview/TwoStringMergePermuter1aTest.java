package com.sharneng.interview;

import java.util.Collection;

public class TwoStringMergePermuter1aTest extends StringMergePermuterTest {

	@Override
	protected Collection<String> merge(String[] inputs) {
		return new TwoStringMergePermuter1a().merge(inputs[0], inputs[1]);
	}

}
