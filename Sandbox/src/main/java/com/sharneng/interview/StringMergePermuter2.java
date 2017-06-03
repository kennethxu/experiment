package com.sharneng.interview;


public class StringMergePermuter2 {
	public static void merge(String s1, String s2) {
		merge("", s1, s2);
	}
	
	private static void merge(String prefix, String s1, String s2) {
		if (s1.length()>0) merge(prefix + s1.charAt(0), s1.substring(1), s2);
		else {
			System.out.println(prefix + s2);
			return;
		}
		if (s2.length()>0) merge(prefix + s2.charAt(0), s1, s2.substring(1));
		else {
			System.out.println(prefix + s1);
			return;
		}
	}
	
	public static void main(String[] args) {
		merge("abcd", "efgh");
	}
}
