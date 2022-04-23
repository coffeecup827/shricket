package org.example.cards;

import org.example.strategy.TimingStrategy;

public class TimingCards extends ICards<TimingStrategy> {
  protected TimingCards() {
    super(TimingStrategy.values());
  }
}
