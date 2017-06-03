package com.sharneng.interview;

import java.util.Collection;

public class TwoStringMergePermuterEricTest extends StringMergePermuterTest {

	@Override
	protected Collection<String> merge(String[] inputs) {
		return new TwoStringMergePermuterEric().merge(inputs[0], inputs[1]);
	}

}
