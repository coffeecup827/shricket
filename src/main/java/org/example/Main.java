package org.example;

import org.example.playground.UserInterface;

public class Main {
  public static void main(String[] args) {
    UserInterface userInterface = getUserInterface();
    userInterface.run();
  }

  public static UserInterface getUserInterface() {
    return new UserInterface();
  }
}
