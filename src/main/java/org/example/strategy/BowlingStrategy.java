package org.example.strategy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum BowlingStrategy implements IStrategy {
  BOUNCER,
  OUT_SWINGER,
  YORKER,
  OFF_BREAK,
  IN_SWINGER,
  LEG_CUTTER,
  SLOWER_BALL,
  PACE,
  OFF_CUTTER,
  DOOSRA;

  static final Set<String> strategies = new HashSet<>();
  public static boolean has(String strategy) {
    if (values().length != strategies.size()) {
      List<String> strategiesList = Arrays.stream(values()).map(Enum::toString).toList();
      strategies.addAll(strategiesList);
    }
    return strategies.contains(strategy);
  }
}
