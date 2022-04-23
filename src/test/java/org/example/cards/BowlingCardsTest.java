package org.example.cards;

import org.example.strategy.BowlingStrategy;

class BowlingCardsTest extends CardsTest<BowlingStrategy, BowlingCards> {
  public BowlingCardsTest() {
    super(new BowlingCards(), BowlingStrategy.values());
  }
}
