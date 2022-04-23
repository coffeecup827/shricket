package org.example.cards;

import org.example.strategy.IStrategy;

import java.util.*;

public abstract class ICards <S extends IStrategy> {
  private final Map<S, Card> cards = new HashMap<>();
  private static final Random random = new Random();
  protected ICards(S[] strategies) {
    List<Integer> uniqueRandomNumbers = getUniqueRandomNumbers(strategies.length);

    for (int index = 0; index < strategies.length; index++) {
      S strategy = strategies[index];
      cards.put(strategy, new Card(strategy.toString(), uniqueRandomNumbers.get(index)));
    }
  }

  private List<Integer> getUniqueRandomNumbers(int size) {
    Set<Integer> uniqueRandomNumbers = new HashSet<>();
    while (uniqueRandomNumbers.size() < size) {
      uniqueRandomNumbers.add(random.nextInt(size)+1);
    }
    return uniqueRandomNumbers.stream().toList();
  }

  public Card getCard(S strategy) {
    return cards.get(strategy);
  }

}
