package org.example.ui_handlers.playground;

import org.example.cards.BattingCards;
import org.example.cards.BowlingCards;
import org.example.cards.TimingCards;
import org.example.strategy.BattingStrategy;
import org.example.strategy.BowlingStrategy;
import org.example.strategy.TimingStrategy;
import org.example.utils.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class SuperOverPlaygroundTest {
  private SuperOverPlayground superOverPlayground;
  private static final BowlingCards bowlingCards = BowlingCards.getInstance();
  private static final BattingCards battingCards = BattingCards.getInstance();
  private static final TimingCards timingCards = TimingCards.getInstance();
  @Captor
  private ArgumentCaptor<String> logsCaptor = ArgumentCaptor.forClass(String.class);
  @BeforeEach
  void setup() {
    superOverPlayground = spy(SuperOverPlayground.class);
  }
  @Test
  void simulationShouldRePromptOnWrongInput() {
    String wrongInput = "SQUARE_CUT wrong_text";
    doReturn(
      "SQUARE_CUT GOOD",
      "UPPER_CUT GOOD",
      "LEG_LANCE GOOD",
      "FLICK GOOD",
      wrongInput,
      "LONG_ON GOOD",

      "SQUARE_CUT GOOD",
      "UPPER_CUT GOOD",
      "LEG_LANCE GOOD",
      "FLICK GOOD",
      "SQUARE_CUT GOOD",
      "LONG_ON GOOD"
    ).when(superOverPlayground).scan();
    superOverPlayground.run();

    verify(superOverPlayground, atLeast(1)).log(logsCaptor.capture());
    List<String> logs = logsCaptor.getAllValues();

    String wrongInputText = Strings.WRONG_INPUT + wrongInput;
    assertTrue(logs.stream().anyMatch(x -> Objects.equals(x, wrongInputText)));
    verify(superOverPlayground, times(12)).scan();
  }
  @Test
  void simulationShouldRePromptOnEmptyInput() {
    doReturn(
      "SQUARE_CUT GOOD",
      "UPPER_CUT GOOD",
      "LEG_LANCE GOOD",
      "FLICK GOOD",
      "",
      "LONG_ON GOOD",

      "SQUARE_CUT GOOD",
      "UPPER_CUT GOOD",
      "LEG_LANCE GOOD",
      "FLICK GOOD",
      "SQUARE_CUT GOOD",
      "LONG_ON GOOD"
    ).when(superOverPlayground).scan();
    superOverPlayground.run();

    verify(superOverPlayground, atLeast(1)).log(logsCaptor.capture());
    List<String> logs = logsCaptor.getAllValues();

    String wrongInputText = Strings.ATLEAST_ONE_EMPTY_INPUT;
    assertTrue(logs.stream().anyMatch(x -> Objects.equals(x, wrongInputText)));
    verify(superOverPlayground, times(12)).scan();
  }

  @Test
  void simulationShouldLogAsWinOnWin() {
    doReturn(getListOfBowls(1)).when(superOverPlayground).getRandomBowls();
    doReturn(5).when(superOverPlayground).getFirstInningsScore();

    doReturn(getShot(1, 1)).when(superOverPlayground).scan();
    superOverPlayground.run();

    verify(superOverPlayground, atLeast(1)).log(logsCaptor.capture());
    List<String> logs = logsCaptor.getAllValues();

    assertTrue(logs.stream().anyMatch(x -> x.contains(Strings.WON_BY_TEXT)));
    verify(superOverPlayground, times(6)).scan();
  }

  @Test
  void simulationShouldLogAsLostOnLoss() {
    doReturn(getListOfBowls(6)).when(superOverPlayground).getRandomBowls();
    doReturn(5).when(superOverPlayground).getFirstInningsScore();

    doReturn(getShot(2, 2)).when(superOverPlayground).scan();
    superOverPlayground.run();

    verify(superOverPlayground, atLeast(1)).log(logsCaptor.capture());
    List<String> logs = logsCaptor.getAllValues();

    assertTrue(logs.stream().anyMatch(x -> x.contains(Strings.LOST_BY_TEXT)));
    verify(superOverPlayground, times(6)).scan();
  }

  @Test
  void simulationShouldLogAsDrawOnDraw() {
    doReturn(getListOfBowls(1)).when(superOverPlayground).getRandomBowls();
    doReturn(6).when(superOverPlayground).getFirstInningsScore();

    doReturn(getShot(1,1 )).when(superOverPlayground).scan();
    superOverPlayground.run();

    verify(superOverPlayground, atLeast(1)).log(logsCaptor.capture());
    List<String> logs = logsCaptor.getAllValues();

    assertTrue(logs.stream().anyMatch(x -> x.contains(Strings.DRAW_TEXT)));
    verify(superOverPlayground, times(6)).scan();
  }

  private String getShot(int battingWeight, int timingWeight) {
    BattingStrategy battingStrategy = null;
    for (BattingStrategy strategy: BattingStrategy.values()) {
      if (battingCards.getCard(strategy).weight() == battingWeight) {
        battingStrategy = strategy;
      }
    }

    TimingStrategy timingStrategy = null;
    for (TimingStrategy strategy: TimingStrategy.values()) {
      if (timingCards.getCard(strategy).weight() == timingWeight) {
        timingStrategy = strategy;
      }
    }

    return (battingStrategy != null ? battingStrategy.name() : "")
      + " "
      + (timingStrategy != null ? timingStrategy.name() : "");
  }

  private List<BowlingStrategy> getListOfBowls(int weight) {
    BowlingStrategy weightedStrategy = null;
    for (BowlingStrategy strategy: BowlingStrategy.values()) {
      if (bowlingCards.getCard(strategy).weight() == weight) {
        weightedStrategy = strategy;
      }
    }

    List<BowlingStrategy> bowls = new ArrayList<>();
    for (int index = 0; index < 6; index++) {
      bowls.add(weightedStrategy);
    }
    return bowls;
  }
}
