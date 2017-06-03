package com.sharneng.interview;

public class HistoricalStockPrice {
	public static int maxGain(int[] prices) {
		int maxGain = 0;
		int sell = 0, buy = 0;
		for(int i = 1; i < prices.length; i++) {
			if (prices[i] < prices[buy]) {
				int gain = prices[sell] - prices[buy];
				if (gain > maxGain) maxGain = gain;
				sell = buy = i;
			} else if (prices[i] > prices[sell]) {
				sell = i;
			}
		}
		int gain = prices[sell] - prices[buy];
		return gain > maxGain ? gain : maxGain;
	}
}
