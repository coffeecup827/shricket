package org.example.ui_handlers.playground;

import org.example.utils.ResultGenerator;
import org.example.utils.Strings;

public class PredictionPlayground extends IPlayground {

  public void run() {
    divider();
    log(Strings.LETS_PLAY_PREDICTION);
    gap();
    log(getEndHelpText());
    log(Strings.INPUT_FORMAT);
    simulate();
    log(Strings.THANKS_PREDICTION);
    divider();
  }
  public void logResult(int score) {
    log(ResultGenerator.getDefaultResult(score));
  }
}
