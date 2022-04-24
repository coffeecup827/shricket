package org.example.playground;

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

  public void logResult(int score) {
    log(ResultGenerator.getCommentary(score) + " - " + ResultGenerator.getDefaultResult(score));
  }
}
