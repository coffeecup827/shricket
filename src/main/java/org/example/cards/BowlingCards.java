package org.example.cards;

import org.example.strategy.BowlingStrategy;

public class BowlingCards extends ICards<BowlingStrategy> {
  private BowlingCards() {
    super(BowlingStrategy.values());
  }

  private static class SingletonHolder {
    private static final BowlingCards INSTANCE = new BowlingCards();
  }

  public static BowlingCards getInstance() {
    return BowlingCards.SingletonHolder.INSTANCE;
  }
}
