package org.example.utils;

import java.util.*;

public class ResultGenerator {

  private ResultGenerator() {}
  private static final Map<Integer, List<String>> scoresToCommentariesMap = new HashMap<>();
  private static final Random random = new Random();

  private static void fillScoreToCommentariesMap() {
    addCommentary(0, "It’s a wicket.");
    addCommentary(0, "Excellent line and length.");
    addCommentary(0, "Edged and taken.");

    String nonBoundaryCommentary = "Excellent running between the wickets.";
    addCommentary(1, nonBoundaryCommentary);

    addCommentary(2, nonBoundaryCommentary);
    addCommentary(2, "Convert ones into twos.");

    addCommentary(3, nonBoundaryCommentary);

    addCommentary(4, "Excellent effort on the boundary.");

    addCommentary(6, "That’s massive and out of the ground.");
    addCommentary(6, "Just over the fielder.");
    addCommentary(6, "It’s a huge hit.");
  }
  private static void addCommentary(int score, String commentary) {
    scoresToCommentariesMap.computeIfAbsent(score, key -> new ArrayList<>());
    scoresToCommentariesMap.get(score).add(commentary);
  }

  public static String getCommentary(int score) {
    if (scoresToCommentariesMap.isEmpty()) {
      fillScoreToCommentariesMap();
    }
    List<String> commentaries = scoresToCommentariesMap.get(score);
    return commentaries.get(random.nextInt(commentaries.size()));
  }

  public static String getDefaultResult(int score) {
    if (score > 0) {
      String text = score == 1 ? " run" : " runs";
      return score + text;
    } else {
      return"1 wicket";
    }
  }
}
