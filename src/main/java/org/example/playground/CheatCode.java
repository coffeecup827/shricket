package org.example.playground;

import org.example.cards.BattingCards;
import org.example.cards.BowlingCards;
import org.example.cards.ICards;
import org.example.cards.TimingCards;
import org.example.strategy.BattingStrategy;
import org.example.strategy.BowlingStrategy;
import org.example.strategy.IStrategy;
import org.example.strategy.TimingStrategy;

public class CheatCode implements ICommand, ILog {
  private final BattingCards battingCards = BattingCards.getInstance();
  private final BowlingCards bowlingCards = BowlingCards.getInstance();
  private final TimingCards timingCards = TimingCards.getInstance();
  @Override
  public void run() {
    divider();
    log("Bowling Strategies Cheat: "  + strategyArrayToString(BowlingStrategy.values(), bowlingCards));
    log("Batting Strategies Cheat: "  + strategyArrayToString(BattingStrategy.values(), battingCards));
    log("Timing Strategies Cheat: "  + strategyArrayToString(TimingStrategy.values(), timingCards));
    divider();
  }

  public <S extends IStrategy> String strategyArrayToString(S[] strategies, ICards<S> cards) {
    StringBuilder text = new StringBuilder();
    for (S strategy: strategies) {
      text.append(strategy.toString())
        .append("-")
        .append(cards.getCard(strategy).weight());
      if (strategy != strategies[strategies.length - 1]) {
        text.append(", ");
      }
    }
    return text.toString();
  }
}
