package org.example.ui_handlers;

import org.example.strategy.BattingStrategy;
import org.example.strategy.BowlingStrategy;
import org.example.strategy.IStrategy;
import org.example.strategy.TimingStrategy;
import org.example.utils.ICommand;
import org.example.utils.ILog;

public class Help implements ICommand, ILog {
  @Override
  public void run() {
    divider();
    log("Bowling Strategies: "  + strategyArrayToString(BowlingStrategy.values()));
    log("Batting Strategies: "  + strategyArrayToString(BattingStrategy.values()));
    log("Timing Strategies: "  + strategyArrayToString(TimingStrategy.values()));
    divider();
  }

  public String strategyArrayToString(IStrategy[] strategies) {
    StringBuilder text = new StringBuilder();
    for (IStrategy strategy: strategies) {
      text.append(strategy.toString());
      if (strategy != strategies[strategies.length - 1]) {
        text.append(", ");
      }
    }
    return text.toString();
  }
}
