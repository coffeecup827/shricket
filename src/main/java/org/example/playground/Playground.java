package org.example.playground;

import org.example.cards.BattingCards;
import org.example.cards.BowlingCards;
import org.example.cards.TimingCards;
import org.example.strategy.BattingStrategy;
import org.example.strategy.BowlingStrategy;
import org.example.strategy.TimingStrategy;

public class Playground implements ICommand, ILog, IRead {
  private final BattingCards battingCards = BattingCards.getInstance();
  private final BowlingCards bowlingCards = BowlingCards.getInstance();
  private final TimingCards timingCards = TimingCards.getInstance();
  public void run() {
    divider();
    log("Lets Play!");
    gap();
    log("Input Format: bowling_strategy batting_strategy timing_strategy");
    simulate();
    divider();
  }

  public void simulate() {
    String input = scan();
    while(!input.equals("END")) {
      String[] strategies = input.split(" ");
      int score = Predictor.predict(
        bowlingCards.getCard(BowlingStrategy.valueOf(strategies[0])),
        battingCards.getCard(BattingStrategy.valueOf(strategies[1])),
        timingCards.getCard(TimingStrategy.valueOf(strategies[2]))
      );
      log(getResult(score));
      input = scan();
    }
  }
  public String getResult(int score) {
    String text;
    if (score > 0) {
      text = score == 1 ? " run" : " runs";
      return score + text;
    } else {
      return  "1 wicket";
    }
  }
}
