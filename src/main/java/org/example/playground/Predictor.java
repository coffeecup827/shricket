package org.example.playground;

import org.example.cards.Card;

public final class Predictor {
  private Predictor() {}
  public static int predict(Card bowlingCard, Card battingCard, Card timingCard) {
    int score = (battingCard.weight() * bowlingCard.weight() * timingCard.weight()) % 6;
    return score == 5 ? 6 : score;
  }
}
