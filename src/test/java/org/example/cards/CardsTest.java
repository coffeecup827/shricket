package org.example.cards;

import org.example.strategy.IStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

abstract class CardsTest<S extends IStrategy, C extends ICards<S>> {

  protected C cards;
  protected S[] strategies;

  public CardsTest(C cards, S[] strategies) {
    this.cards = cards;
    this.strategies = strategies;
  }

  @Test
  void cardsShouldCreateWithUniqueWeights() {
    Set<Integer> weights = new HashSet<>();

    for (S strategy: strategies) {
      weights.add(cards.getCard(strategy).weight());
    }

    assertEquals(strategies.length, weights.size());
  }

  @Test
  void getCardShouldShouldReturnCorrectStrategy() {
    for (S strategy: strategies) {
      assertEquals(strategy.toString(), cards.getCard(strategy).name());
    }
  }

  @Test
  void cardsShouldHavePositiveNonZeroWeight() {
    for (S strategy: strategies) {
      assertTrue(cards.getCard(strategy).weight() > 0);
    }
  }

}
