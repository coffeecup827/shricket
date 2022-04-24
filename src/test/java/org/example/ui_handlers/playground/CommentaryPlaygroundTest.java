package org.example.ui_handlers.playground;

import static org.mockito.Mockito.spy;

class CommentaryPlaygroundTest extends PlaygroundTest {
  @Override
  IPlayground getPlaygroundSpy() {
    return spy(CommentaryPlayground.class);
  }
}
