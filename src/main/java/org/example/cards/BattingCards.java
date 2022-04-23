package org.example.cards;

import org.example.strategy.BattingStrategy;

public class BattingCards extends ICards<BattingStrategy> {
  public BattingCards() {
    super(BattingStrategy.values());
  }
}
