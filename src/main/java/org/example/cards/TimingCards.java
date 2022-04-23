package org.example.cards;

import org.example.strategy.TimingStrategy;

public class TimingCards extends ICards<TimingStrategy> {
  private TimingCards() {
    super(TimingStrategy.values());
  }

  private static class SingletonHolder {
    private static final TimingCards INSTANCE = new TimingCards();
  }

  public static TimingCards getInstance() {
    return TimingCards.SingletonHolder.INSTANCE;
  }
}
