package org.example.playground;

import org.example.cards.Card;
import org.example.strategy.BattingStrategy;
import org.example.strategy.BowlingStrategy;
import org.example.strategy.IStrategy;
import org.example.strategy.TimingStrategy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PredictorTest {

  @ParameterizedTest
  @MethodSource("provideCardsForCorrectPrediction")
  void predictionTextShouldBeCorrect(Card battingCard, Card bowlingCard, Card timingCard, String prediction) {
    assertEquals(prediction, Predictor.predict(battingCard, bowlingCard, timingCard));
  }

  private static Stream<Arguments> provideCardsForCorrectPrediction() {
    return Stream.of(
      Arguments.of(
        getCard(BattingStrategy.FLICK, 6),
        getCard(BowlingStrategy.BOUNCER, 1),
        getCard(TimingStrategy.GOOD, 1),
        "1 wicket"
      ),
      Arguments.of(
        getCard(BattingStrategy.FLICK, 2),
        getCard(BowlingStrategy.BOUNCER, 2),
        getCard(TimingStrategy.GOOD, 1),
        "4 runs"
      ),
      Arguments.of(
        getCard(BattingStrategy.FLICK, 7),
        getCard(BowlingStrategy.BOUNCER, 1),
        getCard(TimingStrategy.GOOD, 1),
        "1 run"
      )
    );
  }

  private static Card getCard(IStrategy strategy, int i) {
    return new Card(strategy.toString(), i);
  }
}
