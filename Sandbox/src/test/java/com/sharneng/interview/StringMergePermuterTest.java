package com.sharneng.interview;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public abstract class StringMergePermuterTest {
	private static Set<String> ArrayToSet(String[] permutation) {
		Set<String> set = new HashSet<String>(permutation.length);
		for (String s : permutation) set.add(s);
		return set;
	}
    public static class Case {
    	public int count;
		public String[] inputs;
		public Set<String> permutation;
		public Case(int count, String[] inputs, Set<String> permutation) {
			this.count = count;
			this.inputs = inputs;
			this.permutation = permutation;
		}
    }
    
    private static String[] case2inputs = new String[]{"djaadfhsoiieerw", "kjakdoaafjheernd"};
	private static final Case case2 = new Case(
		5200300,
		case2inputs,
		null//new HashSet<String>(new StringMergePermuter1().merge(case2inputs))
	);

    private static Case case1 = new Case(
    		70,
    		new String[] {"abcd", "efgh"},
    		ArrayToSet(new String[] {
    				"abcdefgh",
    				"abcedfgh",
    				"abcefdgh",
    				"abcefgdh",
    				"abcefghd",
    				"abecdfgh",
    				"abecfdgh",
    				"abecfgdh",
    				"abecfghd",
    				"abefcdgh",
    				"abefcgdh",
    				"abefcghd",
    				"abefgcdh",
    				"abefgchd",
    				"abefghcd",
    				"aebcdfgh",
    				"aebcfdgh",
    				"aebcfgdh",
    				"aebcfghd",
    				"aebfcdgh",
    				"aebfcgdh",
    				"aebfcghd",
    				"aebfgcdh",
    				"aebfgchd",
    				"aebfghcd",
    				"aefbcdgh",
    				"aefbcgdh",
    				"aefbcghd",
    				"aefbgcdh",
    				"aefbgchd",
    				"aefbghcd",
    				"aefgbcdh",
    				"aefgbchd",
    				"aefgbhcd",
    				"aefghbcd",
    				"eabcdfgh",
    				"eabcfdgh",
    				"eabcfgdh",
    				"eabcfghd",
    				"eabfcdgh",
    				"eabfcgdh",
    				"eabfcghd",
    				"eabfgcdh",
    				"eabfgchd",
    				"eabfghcd",
    				"eafbcdgh",
    				"eafbcgdh",
    				"eafbcghd",
    				"eafbgcdh",
    				"eafbgchd",
    				"eafbghcd",
    				"eafgbcdh",
    				"eafgbchd",
    				"eafgbhcd",
    				"eafghbcd",
    				"efabcdgh",
    				"efabcgdh",
    				"efabcghd",
    				"efabgcdh",
    				"efabgchd",
    				"efabghcd",
    				"efagbcdh",
    				"efagbchd",
    				"efagbhcd",
    				"efaghbcd",
    				"efgabcdh",
    				"efgabchd",
    				"efgabhcd",
    				"efgahbcd",
    				"efghabcd",
    		})
    		);
    
    @Test
    public void testCase1() {
    	runTest(case1);
    }
    
    @Test
    public void testCase2() {
    	runTest(case2);
    }

	private void runTest(Case theCase) {
		Collection<String> result = merge(theCase.inputs);
    	assertThat(result.size(), equalTo(theCase.count));
    	assertThat(new HashSet<String>(result), equalTo(theCase.permutation));
	}
    
    protected abstract Collection<String> merge(String[] inputs);
}
