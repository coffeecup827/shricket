package org.example.ui_handlers.playground;

import org.example.utils.ResultGenerator;
import org.example.utils.Strings;

public class CommentaryPlayground extends IPlayground {

  public void run() {
    divider();
    log(Strings.LETS_PLAY_COMMENTARY);
    gap();
    log(getEndHelpText());
    log(Strings.INPUT_FORMAT);
    simulate();
    log(Strings.THANKS_COMMENTARY);
    divider();
  }

  void logResult(int score) {
    log(ResultGenerator.getCommentary(score) + " - " + ResultGenerator.getDefaultResult(score));
  }
}
