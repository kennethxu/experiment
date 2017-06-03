package com.sharneng.interview;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class HistoricalStockPriceTest {

	@Test
	public void testFlat() {
		assertThat(HistoricalStockPrice.maxGain(new int[]{10, 10, 10, 10}), equalTo(0));
	}
	
	@Test
	public void testUp() {
		assertThat(HistoricalStockPrice.maxGain(new int[]{10, 12, 14, 14, 15, 17}), equalTo(7));
	}

	@Test
	public void testUpDown1() {
		assertThat(HistoricalStockPrice.maxGain(new int[]{10, 12, 14, 16, 15, 10}), equalTo(6));
	}
	
	@Test
	public void testUpDown2() {
		assertThat(HistoricalStockPrice.maxGain(new int[]{10, 12, 14, 16, 15, 10, 8}), equalTo(6));
	}
	
	@Test
	public void testDown() {
		assertThat(HistoricalStockPrice.maxGain(new int[]{10, 9, 9, 7, 5, 4}), equalTo(0));
	}
	
	@Test
	public void testDownUP1() {
		assertThat(HistoricalStockPrice.maxGain(new int[]{10, 9, 7, 5, 6, 8}), equalTo(3));
	}

	@Test
	public void testDownUP2() {
		assertThat(HistoricalStockPrice.maxGain(new int[]{10, 9, 7, 5, 6, 8, 12}), equalTo(7));
	}
	
	@Test
	public void testWave1() {
		assertThat(HistoricalStockPrice.maxGain(new int[]{10, 12, 14, 10, 5, 7, 7}), equalTo(4));
	}
	
	@Test
	public void testWave2() {
		assertThat(HistoricalStockPrice.maxGain(new int[]{10, 12, 14, 10, 5, 7, 10}), equalTo(5));
	}
}
