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
  void predictionScoreShouldBeCorrect(Card battingCard, Card bowlingCard, Card timingCard, int prediction) {
    assertEquals(prediction, Predictor.predict(battingCard, bowlingCard, timingCard));
  }

  private static Stream<Arguments> provideCardsForCorrectPrediction() {
    return Stream.of(
      Arguments.of(
        getCard(BattingStrategy.FLICK, 6),
        getCard(BowlingStrategy.BOUNCER, 1),
        getCard(TimingStrategy.GOOD, 1),
        0
      ),
      Arguments.of(
        getCard(BattingStrategy.FLICK, 2),
        getCard(BowlingStrategy.BOUNCER, 2),
        getCard(TimingStrategy.GOOD, 1),
        4
      ),
      Arguments.of(
        getCard(BattingStrategy.FLICK, 7),
        getCard(BowlingStrategy.BOUNCER, 1),
        getCard(TimingStrategy.GOOD, 1),
        1
      ),
      Arguments.of(
        getCard(BattingStrategy.FLICK, 5),
        getCard(BowlingStrategy.BOUNCER, 1),
        getCard(TimingStrategy.GOOD, 1),
        6
      )
    );
  }

  private static Card getCard(IStrategy strategy, int i) {
    return new Card(strategy.toString(), i);
  }
}
