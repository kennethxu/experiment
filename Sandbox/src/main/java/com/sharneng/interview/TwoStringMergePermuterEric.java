package com.sharneng.interview;

import java.util.function.Consumer;

public class TwoStringMergePermuterEric implements TwoStringMergePermuter {
	public void merge(Consumer<? super String> consumer, String s1, String s2) {
	    int totalLength = s1.length() + s2.length();
	    recursivePermutation(s1, s2, 0, 0, new char[totalLength], consumer);

	}
	private static void recursivePermutation(String s1, String s2, int s1index, int s2index, char[] currOutput, Consumer<? super String> consumer) {
	    boolean done = false;
	    int currindex = s1index + s2index;
	    if (s1.length() == s1index) {
	        done = true;
	        for (int i = s2index; i<s2.length(); i++) {
	        	currOutput[currindex++] = s2.charAt(i);
	        }
	    } else if (s2.length() == s2index) {
	        done = true;
	        for (int i = s1index; i<s1.length(); i++) {
	        	currOutput[currindex++] = s1.charAt(i);
	        }
	    }
	    if (done) {
	        consumer.accept(new String(currOutput));
	        return;
	    }
	    char[] currs1 = currOutput.clone(), currs2 = currOutput.clone();
	    currs1[currindex] = s1.charAt(s1index++);
	    currs2[currindex] = s2.charAt(s2index++);
	    recursivePermutation(s1, s2, s1index, s2index-1, currs1, consumer);
	    recursivePermutation(s1, s2, s1index-1, s2index, currs2, consumer);
	    
	}
}
