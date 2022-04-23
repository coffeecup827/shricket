package org.example.playground;

import org.example.cards.BattingCards;
import org.example.cards.BowlingCards;
import org.example.cards.TimingCards;
import org.example.strategy.BattingStrategy;
import org.example.strategy.BowlingStrategy;
import org.example.strategy.TimingStrategy;

import java.util.Scanner;

public class Playground {
  Scanner scanner = new Scanner(System.in);
  private final BattingCards battingCards = new BattingCards();
  private final BowlingCards bowlingCards = new BowlingCards();
  private final TimingCards timingCards = new TimingCards();
  public void log(String text) {
    System.out.println(text);
  }
  public String scan() {
    return scanner.nextLine().toUpperCase();
  }
  public void divider() {
    log("\n");
    log("----------------------------------------");
    log("\n");
  }
  public void gap() {
    log("");
  }
  public void run() {
    divider();
    log("Lets Play Prediction!");
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
