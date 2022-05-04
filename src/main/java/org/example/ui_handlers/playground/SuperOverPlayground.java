package org.example.ui_handlers.playground;

import org.example.strategy.BattingStrategy;
import org.example.strategy.BowlingStrategy;
import org.example.strategy.TimingStrategy;
import org.example.ui_handlers.IUserAction;
import org.example.utils.Predictor;
import org.example.utils.ResultGenerator;
import org.example.utils.Strings;

import java.util.*;

public class SuperOverPlayground extends IPlayground implements IUserAction {

  private static final List<BowlingStrategy> bowls = new ArrayList<>();
  private static final int NUMBER_OF_BALLS = 6;
  private static final Random random = new Random();
  private int firstInningsScore = 0;
  private static final int WICKETS_AVAILABLE = 2;
  private static final String[] batters = new String[] {
    Strings.FIRST_PLAYER,
    Strings.SECOND_PLAYER,
    Strings.THIRD_PLAYER
  };
  private static final String BOWLER = Strings.BOWLER;

  private String batsmanOne;
  private String batsmanTwo;
  private String currentBatsman;
  private int currentBallNumber;
  private int wicketsTaken;
  private int totalRunsScored;

  public void run() {
    divider();
    log(Strings.LETS_PLAY_SUPER_OVER);
    gap();

    simulate();

    gap();
    log(Strings.THANKS_SUPER_OVER);
    divider();
  }
  @Override
  void simulate() {
    List<List<String>> inputs = new ArrayList<>();

    initializeSuperOver();
    logSuperOverInfo();
    getValidInput(inputs);
    runSimulation(inputs);

    gap();
    logInningsResult();
  }

  private void logInningsResult() {
    log("Australia scored: " + totalRunsScored + " runs.");
    if (totalRunsScored > firstInningsScore) {
      log(Strings.WON_BY_TEXT + (wicketsTaken == 0 ? 2 : 1) + " wicket.");
    } else if(totalRunsScored < firstInningsScore) {
      log(Strings.LOST_BY_TEXT + (firstInningsScore - totalRunsScored) + " runs.");
    } else {
      log(Strings.DRAW_TEXT);
    }
  }

  private void runSimulation(List<List<String>> inputs) {
    while (currentBallNumber < 6 && wicketsTaken < WICKETS_AVAILABLE && totalRunsScored < firstInningsScore + 1) {
      List<String> input = inputs.get(currentBallNumber);
      BowlingStrategy bowlingStrategy = bowls.get(currentBallNumber);
      BattingStrategy battingStrategy = BattingStrategy.valueOf(input.get(0));
      TimingStrategy timingStrategy = TimingStrategy.valueOf(input.get(1));

      int score = Predictor.predict(
        bowlingCards.getCard(bowlingStrategy),
        battingCards.getCard(battingStrategy),
        timingCards.getCard(timingStrategy)
      );

      gap();
      log(BOWLER + " bowled " + bowlingStrategy.name() + " ball,");
      log(currentBatsman + " played " + timingStrategy.name() + " " + battingStrategy.name() + " shot,");
      log(ResultGenerator.getCommentary(score) + " - " + ResultGenerator.getDefaultResult(score) + ".");

      if (score == 0) {
        wicketsTaken++;
        switchBatsmanOnWicket();
      }

      swapBatsmanOnOddRuns(score);

      totalRunsScored += score;
      currentBallNumber++;
    }
  }

  private void switchBatsmanOnWicket() {
    if (currentBatsman.equals(batsmanOne)) {
      batsmanOne = batters[2];
    } else {
      batsmanTwo = batters[2];
    }
    currentBatsman = batters[2];
  }

  private void swapBatsmanOnOddRuns(int score) {
    if (score % 2 == 1) {
      currentBatsman = currentBatsman.equals(batsmanOne) ? batsmanTwo : batsmanOne;
    }
  }

  private void getValidInput(List<List<String>> inputs) {
    boolean isAllInputsAreValid = false;

    while (!isAllInputsAreValid) {
      isAllInputsAreValid = true;
      inputs.clear();
      log(Strings.SUPER_OVER_INPUT_HELP);
      log(Strings.SUPER_OVER_INPUT_FORMAT);
      gap();

      Set<String> invalidInputs = new HashSet<>();
      for (int index = 0; index < NUMBER_OF_BALLS; index++) {
        String scannedInput = scan();
        String[] inputArray = scannedInput.split(" ");
        List<String> input = Arrays.stream(inputArray).toList();

        boolean isCurrentInputValid = inputArray.length == 2
          && BattingStrategy.has(input.get(0))
          && TimingStrategy.has(input.get(1));

        if (isCurrentInputValid) {
          inputs.add(input);
        } else {
          invalidInputs.add(scannedInput);
        }

        isAllInputsAreValid = isAllInputsAreValid && isCurrentInputValid;
      }

      logInvalidInputsIfAny(invalidInputs);
    }
  }

  private void logInvalidInputsIfAny(Set<String> invalidInputs) {
    if (!invalidInputs.isEmpty()) {
      invalidInputs.forEach(invalidInput -> {
        if (invalidInput.isEmpty()) {
          log(Strings.ATLEAST_ONE_EMPTY_INPUT);
        } else {
          log(Strings.WRONG_INPUT + invalidInput);
        }
      });
    }
  }

  private void logSuperOverInfo() {
    log("INDIA 11 Score: " + firstInningsScore + " runs");
    log("Target: " + (firstInningsScore + 1) + " runs");
    log("Wickets available: " + WICKETS_AVAILABLE);
    gap();
    StringBuilder chosenBowlsString = new StringBuilder();
    for (int index = 0; index < bowls.size(); index++) {
      chosenBowlsString.append(bowls.get(index).name());
      if (index != bowls.size() - 1) {
        chosenBowlsString.append(", ");
      }
    }
    log("Bowling Cards: " + chosenBowlsString);
    gap();
  }

  private void initializeSuperOver() {
    batsmanOne = batters[0];
    batsmanTwo = batters[1];
    currentBatsman = batsmanOne;
    currentBallNumber = 0;
    wicketsTaken = 0;
    totalRunsScored = 0;

    bowls.clear();
    bowls.addAll(getRandomBowls());
    firstInningsScore = getFirstInningsScore();
  }

  int getFirstInningsScore() {
    return random.nextInt(12);
  }

  List<BowlingStrategy> getRandomBowls() {
    List<BowlingStrategy> randomBowls = new ArrayList<>();
    for (int index = 0; index < NUMBER_OF_BALLS; index++) {
      randomBowls.add(BowlingStrategy.values()[random.nextInt(NUMBER_OF_BALLS)]);
    }
    return randomBowls;
  }
  void logResult(int score) { /* Not Used */ }

  @Override
  public String getName() {
    return "Play Super Over";
  }
}
