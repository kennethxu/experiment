package com.sharneng.interview.blackjack;

import java.util.List;

public interface Hand {
	public List<Card> getCards();
	public int getBetAmount();
	public boolean getResult();
}
