package com.example.cards;

import org.example.cards.BattingCards;
import org.example.strategy.BattingStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

class CardsTest {
  @Test
  void cardsShouldCreateWithUniqueWeights() {
    BattingCards cards = new BattingCards();
    Set<Integer> weights = new HashSet<>();

    for (BattingStrategy strategy: BattingStrategy.values()) {
      weights.add(cards.getCard(strategy).weight());
    }

    assertEquals(BattingStrategy.values().length, weights.size());
  }
}
