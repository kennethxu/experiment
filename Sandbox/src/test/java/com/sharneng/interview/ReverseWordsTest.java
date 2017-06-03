package com.sharneng.interview;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class ReverseWordsTest {
	private static final Charset charset = StandardCharsets.UTF_8;
	
	@Test
	public void test() {
		byte[] input = "  A  lazy fox  ".getBytes();
		byte[] result = ReverseWords.reverseWords(input);
		assertThat(new String(result, charset), equalTo("  fox lazy  A  "));
	}
}
