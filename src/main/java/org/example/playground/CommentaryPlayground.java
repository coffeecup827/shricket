package org.example.playground;

public class CommentaryPlayground extends IPlayground {
  public void run() {
    divider();
    log("Lets Play With Commentary!");
    gap();
    log("Input Format: bowling_strategy batting_strategy timing_strategy");
    simulate();
    divider();
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
