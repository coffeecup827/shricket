package org.example.playground;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentaryPlaygroundTest {
  private final CommentaryPlayground commentaryPlayground =  new CommentaryPlayground();
  @ParameterizedTest
  @MethodSource("provideScoreForCorrectCommentary")
  void resultTextShouldBeCorrect(int score, String result) {
    assertEquals(result, commentaryPlayground.getResult(score));
  }

  private static Stream<Arguments> provideScoreForCorrectCommentary() {
    return Stream.of(
      Arguments.of(0, "1 wicket"),
      Arguments.of(3, "3 runs"),
      Arguments.of(1, "1 run"),
      Arguments.of(6, "6 runs")
    );
  }
}
