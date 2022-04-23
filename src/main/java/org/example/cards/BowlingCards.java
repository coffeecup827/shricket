package org.example.cards;

import org.example.strategy.BowlingStrategy;

public class BowlingCards extends ICards<BowlingStrategy> {
  public BowlingCards() {
    super(BowlingStrategy.values());
  }
}
