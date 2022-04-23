package org.example.cards;

import org.example.strategy.TimingStrategy;

public class TimingCards extends ICards<TimingStrategy> {
  public TimingCards() {
    super(TimingStrategy.values());
  }
}
