package com.sharneng.interview;

import java.util.Collection;

public class StringMergePermuter3Test extends StringMergePermuterTest {

	@Override
	protected Collection<String> merge(String[] inputs) {
		return new StringMergePermuter3().merge(inputs);
	}

}
