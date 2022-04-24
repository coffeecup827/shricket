package org.example.ui_handlers.playground;

import org.example.cards.Card;
import org.example.ui_handlers.playground.IPlayground;
import org.example.utils.Predictor;
import org.example.utils.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public abstract class PlaygroundTest {
  private static IPlayground playground;

  @BeforeEach
  void setup() {
    playground = getPlaygroundSpy();
  }

  abstract IPlayground getPlaygroundSpy();

  @Captor
  private ArgumentCaptor<String> logsCaptor = ArgumentCaptor.forClass(String.class);
  @Captor
  private ArgumentCaptor<Card> battingCardCaptor = ArgumentCaptor.forClass(Card.class);
  @Captor
  private ArgumentCaptor<Card> bowlingCardCaptor = ArgumentCaptor.forClass(Card.class);
  @Captor
  private ArgumentCaptor<Card> timingCardCaptor = ArgumentCaptor.forClass(Card.class);


  @Test
  void simulationShouldStopOnEnd() {
    doReturn("DOOSRA SQUARE_CUT GOOD", Strings.END_TEXT).when(playground).scan();
    playground.run();

    verify(playground, times(2)).scan();
  }

  @Test
  void simulationShouldRePromptOnWrongInput() {
    doReturn(
      "DOOSRA wrong_text GOOD",
      "DOOSRA SQUARE_CUT GOOD",
      Strings.END_TEXT
    ).when(playground).scan();
    playground.run();

    verify(playground, atLeast(1)).log(logsCaptor.capture());
    List<String> logs = logsCaptor.getAllValues();
    assertTrue(logs.stream().anyMatch(x -> Objects.equals(x, Strings.VALID_INPUT_HELP)));
    verify(playground, times(3)).scan();
  }

  @Test
  void simulationFindsCorrectCards() {
    try(MockedStatic<Predictor> predictorMockedStatic = mockStatic(Predictor.class)) {
      predictorMockedStatic.when(
        () -> Predictor.predict(bowlingCardCaptor.capture(), battingCardCaptor.capture(),  timingCardCaptor.capture())
      ).thenReturn(1);
      doReturn(
        "DOOSRA LONG_ON LATE",
        Strings.END_TEXT
      ).when(playground).scan();
      playground.run();
    }

    assertEquals("DOOSRA", bowlingCardCaptor.getValue().name());
    assertEquals("LONG_ON", battingCardCaptor.getValue().name());
    assertEquals("LATE", timingCardCaptor.getValue().name());
  }
}
