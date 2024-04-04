import java.util.Arrays;

public class AIPlayer extends Player {

    public AIPlayer() {
        this.scorecard = new Scorecard();
        this.playerNumber = playerCount;
        playerCount ++;
        this.playerName = "Computer Player";

    }


    public void runAITurn() {

        boolean[] diceChoices = {false, false, false, false, false};
        int[] diceHand = {0, 0, 0, 0, 0};
        System.out.println("It's " + playerName + "'s turn");
        diceHand = diceRolls(diceChoices, diceHand);
        printDice(diceHand);
        int scoreChoice = aIChooseCategory(diceHand);
        System.out.println(scoreChoice);
        enterScore(scoreChoice, scorecard.calculateScore(diceHand, scoreChoice));
        System.out.println("Computer's Scorecard");
        scorecard.printScorecard();
    }

    private int findEmptyScore() {
        for (int i = 0; i < 14; i++) {
            if (scorecard.getScore(i).isEmpty()) {
                return i + 1;
            }
        }

        return 0;
    }

    public int aIChooseCategory (int[] diceHand) {
        int[] potentialScores = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        int highestScore = -1;
        int highestScoreCategory = -1;

        for(int i = 0; i < 13; i++ ) {
            if(scorecard.getScore(i).isEmpty()) potentialScores[i] = scorecard.calculateScore(diceHand, i + 1);
        }
        System.out.println(Arrays.toString(potentialScores));
        for(int i = 0; i < 13; i++ ) {
            if(potentialScores[i] > highestScore) {
                highestScoreCategory = i + 1;
                highestScore = potentialScores[i];
            }
        }

        return highestScoreCategory;
    }
}
