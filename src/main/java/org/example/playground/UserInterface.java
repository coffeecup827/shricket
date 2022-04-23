package org.example.playground;

import java.util.ArrayList;
import java.util.List;

public class UserInterface implements ICommand, ILog, IRead {
  private final List<ICommand> actions = new ArrayList<>();
  private final int totalOptions;
  public UserInterface() {
    actions.add(getPlayground());
    actions.add(getCommentaryPlayground());
    actions.add(getHelp());
    actions.add(getCheatCode());
    totalOptions = actions.size() + 1;
  }

  @Override
  public void run() {
    promptForOption();

    int option = getOption();

    while (option != totalOptions) {
      actions.get(option - 1).run();
      promptForOption();
      option = getOption();
    }
  }

  public int getOption() {
    log("Your Option: ");
    int option = Integer.parseInt(scan());
    while (option > totalOptions || option < 1) {
      log("Please select a valid option");
      log("Your Option: ");
      option = Integer.parseInt(scan());
    }
    return option;
  }

  public void promptForOption() {
    log("Please select an option");
    log("1. Prediction");
    log("2. Commentary");
    log("3. Help");
    log("4. Cheat Code");
    log("5. Stop");
  }

  private PredictionPlayground getPlayground() {
    return new PredictionPlayground();
  }
  public CheatCode getCheatCode() {
    return new CheatCode();
  }
  public Help getHelp() {
    return new Help();
  }
  private CommentaryPlayground getCommentaryPlayground() {
    return new CommentaryPlayground();
  }
}
