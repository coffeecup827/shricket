package org.example.playground;

import org.example.cards.Card;

public final class Predictor {
  private Predictor() {}
  public static String predict(Card battingCard, Card bowlingCard, Card timingCard) {
    int score = (battingCard.weight() * bowlingCard.weight() * timingCard.weight()) % 6;
    String text;

    if (score > 0) {
      text = score == 1 ? " run" : " runs";
      return score + text;
    } else {
      return  "1 wicket";
    }
  }
}
