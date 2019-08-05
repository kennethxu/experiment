package com.sharneng.interview;

public class HistoricalStockPrice {
    public static int maxGain2(int[] prices) {
        int maxGain = 0;
        for(int sell = 0, buy = 0, i = 1; i <= prices.length; i++) {
            if (i == prices.length || prices[i] < prices[buy]) { // end or new low
                int gain = prices[sell] - prices[buy]; // last section gain
                if (gain > maxGain) maxGain = gain;
                sell = buy = i; // start a new section
            } else if (prices[i] > prices[sell]) {
                sell = i;
            }
        }
        return maxGain;
    }

    public static int maxGain(int[] prices) {
        Trade best = findSingleBestTrade(prices);
        return best == null ? 0 : prices[best.sell] - prices[best.buy];
    }

    public static class Trade {
    	public final int buy, sell;

        private Trade(int buy, int sell) {
            this.buy = buy;
            this.sell = sell;
        }
    }

    public static Trade findSingleBestTrade(final int[] prices) {
        if (prices == null || prices.length == 0) return null;
        
        Trade best = dummy;  
        int sell = 0, buy = 0;
        
		for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[buy]) { // use <= to get the shortest holding time
                // we encountered a new low price that starts a new segment
            	best = bestTrade(prices, best, buy, sell);
                sell = buy = i; // start a new segment
            } else if (prices[i] > prices[sell]) {
                sell = i;
            }
        }
        best = bestTrade(prices, best, buy, sell);
        
        return best == dummy ? null : best;
    }
    
    private static final Trade dummy = new Trade(0, 0);
    
    // compare last segment trade with the known best and register if in favor
    private static Trade bestTrade(int[] prices, Trade best, int buy, int sell) {
    	if (sell == buy) return best; // a minor optimization and optional.
        int bestProfit = prices[best.sell] - prices[best.buy];
        int profit = prices[sell] - prices[buy];
        // for shortest holding time, use 
        // profit > bestProfit || profit == bestProfit && sell-buy <= best.sell-best.buy
        return profit > bestProfit ? new Trade(buy, sell) : best;
    }
    
}