package org.example.ui_handlers;

import org.example.ui_handlers.playground.CommentaryPlayground;
import org.example.ui_handlers.playground.PredictionPlayground;
import org.example.ui_handlers.playground.SuperOverPlayground;
import org.example.utils.ICommand;
import org.example.utils.ILog;
import org.example.utils.IRead;

import java.util.ArrayList;
import java.util.List;

public class UserInterface implements ICommand, ILog, IRead {
  private final List<IUserAction> actions = new ArrayList<>();
  private final int totalOptions;
  public UserInterface() {
    actions.add(getPlayground());
    actions.add(getCommentaryPlayground());
    actions.add(getSuperOverPlayground());
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
    int option = getAnInt();
    while (option > totalOptions || option < 1) {
      log("Please select a option");
      log("Your Option: ");
      option = getAnInt();
    }
    return option;
  }

  private int getAnInt() {
    int option = 0;
    try {
      option = Integer.parseInt(scan());
    } catch (Exception e) {
      log("Please input option number");
      gap();
    }
    return option;
  }

  public void promptForOption() {
    log("Please select an option");
    for (int i = 1; i <= actions.size(); i++) {
      log(i + ". " + actions.get(i - 1).getName());
    }
    log((actions.size() + 1) + ". Stop");
    log("For more info, read readme :)");
    gap();
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
  private SuperOverPlayground getSuperOverPlayground() {
    return new SuperOverPlayground();
  }
}
