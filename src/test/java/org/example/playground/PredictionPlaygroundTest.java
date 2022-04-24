package org.example.playground;

import static org.mockito.Mockito.spy;

class PredictionPlaygroundTest extends PlaygroundTest {
  @Override
  IPlayground getPlaygroundSpy() {
    return spy(PredictionPlayground.class);
  }
}
