package com.sharneng.experiment.sort;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class d {
	
	public static class A {
		protected void foo() {System.out.println("A");}
		public void bar() { foo(); }
	}
	
	public static class B extends A {
		public void foo() {System.out.println("B");}
	}
	
	
	
	public static void main(String[] args)
	{
		new B().bar();
		//String input = "applepieamanclassnamestochaintothestrutsvelocitycontext";
		String input = "drawerstorequire";
		for(String s : addMissingSpaces(input))
		{
			System.out.println(s);
			
		}
	}
	
	public static List<String> addMissingSpaces(String s)
	{
		if (s == null) {
			throw new IllegalArgumentException("parameter s must not be null");
		}
		
		List<String> result = new ArrayList<String>();
		addMissingSpaces(result, s, new StringBuilder());
		return result;
	}
	
	private static void addMissingSpaces(List<String> result, String s, StringBuilder prefix)
	{
		int size = prefix.length();
		if (d.isValid(s)) result.add(prefix.append(' ').append(s).toString());
		for(int end = s.length(), i = 1; i < end; i++)
		{
			String left = s.substring(0, i);
			if (!d.isValid(left)) continue;
			prefix.setLength(size);
			prefix.append(' ').append(left);
			addMissingSpaces(result, s.substring(i), prefix);
		}
	}
	private static Set<String> words = initWords();
	private static Set<String> initWords()
	{
		Set<String> words = new HashSet<String>();
		words.add("a");
		words.add("an");
		words.add("man");
		words.add("am");
		words.add("apple");
		words.add("pie");
		words.add("i");
		words.add("to");
		words.add("chain");
		words.add("classnames");
		words.add("class");
		words.add("drawer");
		words.add("drawers");
		words.add("store");
		words.add("require");
		words.add("quire");
		words.add("the");
		words.add("struts");
		words.add("city");
		words.add("velocity");
		words.add("context");
		words.add("text");
		return words;
	}
	private static boolean isValid(String s)
	{
		return words.contains(s);
	}
}
