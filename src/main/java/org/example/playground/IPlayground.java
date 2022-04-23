package org.example.playground;

import org.example.cards.BattingCards;
import org.example.cards.BowlingCards;
import org.example.cards.TimingCards;
import org.example.strategy.BattingStrategy;
import org.example.strategy.BowlingStrategy;
import org.example.strategy.TimingStrategy;

public abstract class IPlayground implements ICommand, ILog, IRead {
  protected final BattingCards battingCards = BattingCards.getInstance();
  protected final BowlingCards bowlingCards = BowlingCards.getInstance();
  protected final TimingCards timingCards = TimingCards.getInstance();

  public void simulate() {
    log("Your Input: ");
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

  public abstract String getResult(int score);
}
