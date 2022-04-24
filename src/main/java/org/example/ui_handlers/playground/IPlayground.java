package org.example.ui_handlers.playground;

import org.example.cards.BattingCards;
import org.example.cards.BowlingCards;
import org.example.cards.TimingCards;
import org.example.strategy.BattingStrategy;
import org.example.strategy.BowlingStrategy;
import org.example.strategy.TimingStrategy;
import org.example.utils.*;

public abstract class IPlayground implements ICommand, ILog, IRead {
  protected final BattingCards battingCards = BattingCards.getInstance();
  protected final BowlingCards bowlingCards = BowlingCards.getInstance();
  protected final TimingCards timingCards = TimingCards.getInstance();

  void simulate() {
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

  private String getWrongInputText(String[] strategies) {
    StringBuilder text = new StringBuilder();
    for (String strategy : strategies) {
      if (!strategy.isEmpty()) {
        text.append(strategy).append(" ");
      }
    }

    if (text.toString().isEmpty()) {
      return Strings.EMPTY_INPUT;
    } else {
      return text + Strings.IS_WRONG;
    }
  }
  String getEndHelpText() {
    return Strings.END_HELP_1 + Strings.END_TEXT + Strings.END_HELP_2;
  }

  private boolean validInput(String[] strategies) {
    return strategies.length == 3 && BowlingStrategy.has(strategies[0])
      && BattingStrategy.has(strategies[1])
      && TimingStrategy.has(strategies[2]);
  }

  abstract void logResult(int score);
}
