package com.sharneng.interview.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Round {
	private final Dealer dealer;
	private List<Card> dealerHand;
	private final Map<Player, Hand> hands;
	
	private static class HandImpl implements Hand {
		private final List<Card> cards = new ArrayList<>();
		private List<Card> immutableList;
		private final int betAmount;
		private boolean result;
		
		private HandImpl(int betAmount) {
			this.betAmount = betAmount;
		}

		@Override
		public List<Card> getCards() {
			if (immutableList == null) immutableList = Collections.unmodifiableList(cards);
			return immutableList;
		}

		@Override
		public int getBetAmount() {
			return betAmount;
		}

		@Override
		public boolean getResult() {
			return result;
		}
	}
	
	public Round(Dealer dealer, Map<Player, Integer> bets) {
		this.dealer = dealer;
		hands = new LinkedHashMap<>(bets.size());
		for(Map.Entry<Player, Integer> bet : bets.entrySet()) {
			hands.put(bet.getKey(), new HandImpl(bet.getValue()));
		}
	}
}
