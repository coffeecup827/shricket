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
    log(Strings.YOUR_INPUT);
    String input = scan();
    while(!input.equals(Strings.END_TEXT)) {
      String[] strategies = input.split(" ");
      if (validInput(strategies)) {
        int score = Predictor.predict(
          bowlingCards.getCard(BowlingStrategy.valueOf(strategies[0])),
          battingCards.getCard(BattingStrategy.valueOf(strategies[1])),
          timingCards.getCard(TimingStrategy.valueOf(strategies[2]))
        );
        logResult(score);
      } else {
        log(getWrongInputText(strategies));
        log(Strings.VALID_INPUT_HELP);
        log(getEndHelpText());
        log(Strings.INPUT_FORMAT);
      }

      gap();
      log(Strings.YOUR_INPUT);
      input = scan();
    }
  }

  public String getWrongInputText(String[] strategies) {
    return strategies[0] + " " + strategies[1] + " " + strategies[2] + Strings.IS_WRONG;
  }
  public String getEndHelpText() {
    return Strings.END_HELP_1 + Strings.END_TEXT + Strings.END_HELP_2;
  }

  private boolean validInput(String[] strategies) {
    return BowlingStrategy.has(strategies[0])
      && BattingStrategy.has(strategies[1])
      && TimingStrategy.has(strategies[2]);
  }

  public abstract void logResult(int score);
}
