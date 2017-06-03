package com.sharneng.interview;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ReverseWords {
	public static byte[] reverseWords(byte[] input) {
	    if (input == null) throw new IllegalArgumentException("input is null");
	    final Charset charset = StandardCharsets.UTF_8;
	    
	    String[] words = new String(input, charset).split(" ", -1);
	    if (words.length == 0) {
	    	return new byte[0];
	    }
	    
	    StringBuilder sb = new StringBuilder(input.length);
	    int index = words.length-1;
	    sb.append(words[index--]); // out of loop to eliminate leading space.
	    while(index >= 0) {
	        sb.append(" ").append(words[index--]);
	    }
	    
	    return sb.toString().getBytes(charset); 
	}
}
