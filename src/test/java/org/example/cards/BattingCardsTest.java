package org.example.cards;

import org.example.strategy.BattingStrategy;

class BattingCardsTest extends CardsTest<BattingStrategy, BattingCards> {
  public BattingCardsTest() {
    super(BattingCards.getInstance(), BattingStrategy.values());
  }
}
