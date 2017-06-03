package com.sharneng.interview;

import java.util.Collection;

public class TwoStringMergePermuter2bTest extends StringMergePermuterTest {

	@Override
	protected Collection<String> merge(String[] inputs) {
		return new TwoStringMergePermuter2b().merge(inputs[0], inputs[1]);
	}

}
