package org.example.cards;

import org.example.strategy.TimingStrategy;

public class TimingCardsTest extends CardsTest<TimingStrategy, TimingCards> {
  public TimingCardsTest() {
    super(new TimingCards(), TimingStrategy.values());
  }
}
