
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
        int scoreChoice = findEmptyScore();
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
}
