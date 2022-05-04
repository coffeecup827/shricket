### Main Meny Quick Help:
 - **Play Prediction** - Starts the default game
 - **Play with Commentary** - Adds commentary to the prediction game
 - **Play Super Over** - Play Super Over mode
 - **Help** - Shows all the available batting, bowling and timing strategies
 - **Cheat Code** - Shows the strategies with their weights
 - **Stop** - Shuts the app down

### Prediction Logic:

All the strategies are given a random weight at the boot up of the app. This 
weight will remain constant for the given strategy throughout the app's 
lifetime. This logic can be found at `ICards.getUniqueRandomNumbers` and this 
weight can be seen with the help of Cheat Code option on the menu. 
`Predictor.predict` takes in three cards, multiples them and divided 
them by 6 to get a reminder which is then mapped to its respective run as 
shown below.
- 0 - wicket
- 1 - 1 run
- 2 - 2 runs
- 3 - 3 runs
- 4 - 4 runs
- 5 - 6 runs

### Commentary Logic:

Commentaries for a given run/wicket is added to a map in `ResultGenerator` and
a commentary is chosen randomly based on the number of commentaries available
for the given score.

### Prediction GamePlay:

A non-ending game until stopped by entering the text `END` (It's not
case-sensitive). None of the text inputs in the app are case-sensitive, they
all are converted to capital letters and then used internally. Input a bowling,
batting and timing strategy, app will show the runs scored. The available
strategies can be seen by choosing `Help` in main menu.

### Commentary GamePlay:

This is an extension to Prediction Gameplay but with Commentary. Instead of 
only showing the runs scored, it will display a randomly chosen commentary 
for the runs scored. The commentaries can be configured in `ResultGenerator`

### SuperOver GamePlay:

This is limited gameplay with `6` playable bowls. The bowls are chosen randomly
at the start of the gameplay everytime and shown. The number of runs scored by
the batting team is randomly chosen below `12` (Configurable) and is shown. 

It takes in 6 batting and timing strategies as inputs. **Once all of them** 
are inputted the app predicts the players choices against the bowls in order 
and predicts a score. This happens until either if 2 wickets are gone or if 
more runs are score or if bowls get over before the required runs is reached.

Each prediction is displayed with a commentary along with bowler and batsman
details. The names can be updated in `Strings` file. The batsman names get
switched whenever an odd run is scored.

### How To Use Cheat Codes:

Cheat data can be obtained by choosing the `Cheat Code` option in the main 
menu. This will list all the available strategies with their given weights.
This can be used to cheat the scores in any game mode.

Say a score `4` is needed, chose three cards with weights as `1,1,4` or 
`2,2,4`. This will result in a score of 4 as `1*1*1=4` and `4%6=4` or `2*2*4=16`
and `16%6=4`. With this way scores can be manipulated with the cheat codes.






