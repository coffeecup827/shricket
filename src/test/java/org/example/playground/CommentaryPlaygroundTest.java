package org.example.playground;

import static org.mockito.Mockito.spy;

class CommentaryPlaygroundTest extends PlaygroundTest {
  @Override
  IPlayground getPlaygroundSpy() {
    return spy(CommentaryPlayground.class);
  }
}
