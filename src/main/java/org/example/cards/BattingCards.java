package org.example.cards;

import org.example.strategy.BattingStrategy;

public class BattingCards extends ICards<BattingStrategy> {
  private BattingCards() {
    super(BattingStrategy.values());
  }

  private static class SingletonHolder {
    private static final BattingCards INSTANCE = new BattingCards();
  }

  public static BattingCards getInstance() {
    return SingletonHolder.INSTANCE;
  }
}
