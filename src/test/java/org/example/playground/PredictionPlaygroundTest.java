package org.example.playground;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

class PredictionPlaygroundTest {
  private final PredictionPlayground predictionPlayground =  new PredictionPlayground();
  @ParameterizedTest
  @MethodSource("provideScoreForCorrectPrediction")
  void resultTextShouldBeCorrect(int score, String result) {
    assertEquals(result, predictionPlayground.getResult(score));
  }

  private static Stream<Arguments> provideScoreForCorrectPrediction() {
    return Stream.of(
      Arguments.of(0, "1 wicket"),
      Arguments.of(3, "3 runs"),
      Arguments.of(1, "1 run"),
      Arguments.of(6, "6 runs")
    );
  }
}
