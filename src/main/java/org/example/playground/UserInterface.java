package org.example.playground;

public class UserInterface implements ICommand, ILog, IRead {

  private final Help help = getHelp();
  private final CheatCode cheatCode = getCheatCode();
  private final Playground playground = getPlayground();

  private Playground getPlayground() {
    return new Playground();
  }
  public CheatCode getCheatCode() {
    return new CheatCode();
  }
  public Help getHelp() {
    return new Help();
  }

  @Override
  public void run() {
    promptForOption();

    int option = getOption();

    while (option != 4) {
      switch (option) {
        case 1 -> playground.run();
        case 2 -> help.run();
        case 3 -> cheatCode.run();
        default -> log("Please a valid option");
      }
      promptForOption();
      option = getOption();
    }
  }

  public int getOption() {
    log("Your Option: ");
    int option = Integer.parseInt(scan());
    while (option > 4 || option < 1) {
      log("Please select a valid option");
      log("Your Option: ");
      option = Integer.parseInt(scan());
    }
    return option;
  }

  public void promptForOption() {
    log("Please select an option");
    log("1. Prediction");
    log("2. Help");
    log("3. Cheat Code");
    log("4. Stop");
  }
}
