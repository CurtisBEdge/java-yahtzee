import java.util.*;

public class Player {

    private final PlayerInput input;
    private static Integer playerCount = 1;

    private Integer playerNumber;

    private Scorecard scorecard;

    private String playerName;

    private int finalScore;

    public Player() {
        this.scorecard = new Scorecard();
        this.input = new PlayerInput();
        this.playerNumber = playerCount;
        playerCount ++;
    }

    public Integer getPlayerNumber() {
        return playerNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String newPlayerName) {
        this.playerName = newPlayerName;
    }

    public void runTurn() {
        int rolls = 1;
        boolean[] diceChoices = {false, false, false, false, false};
        int[] diceHand = {0, 0, 0, 0, 0};
        scorecard.printScorecard();
        System.out.println("It's " + playerName + "'s turn.");
        do {
            diceHand = diceRolls(diceChoices, diceHand);
            diceChoices = chooseDice();
            rolls ++;
            System.out.println(rolls);
            System.out.println(keepAllDice(diceChoices));
        } while ((rolls < 3) & (!keepAllDice(diceChoices)));
        scorecard.printScorecard();
        printDice(diceHand);
        int scoreChoice = chooseScorecardCategory();
        int calculatedScore = scorecard.calculateScore(diceHand, scoreChoice);
        enterScore(scoreChoice, calculatedScore);

    }

    public int[] diceRolls(boolean[] diceChoices, int[] diceHand) {
        Random rand = new Random();
        int upperbound = 6;
        for (int i = 0; i < 5 ; i++) {
            if (!diceChoices[i]) {
                diceHand[i] = (rand.nextInt(upperbound)) + 1;
            }
        }
        printDice(diceHand);
        return diceHand;
    }

    private boolean[] chooseDice() {
        boolean[] diceChoices = {false, false, false, false, false};
        String choiceInput = input.inputDiceChoice();
        if (choiceInput.contains("6")) {
            System.out.println(Arrays.toString(diceChoices));
            return diceChoices;
        }
        else if (choiceInput.contains("0")) {
            for (int i = 0; i < 5; i++) {
                diceChoices[i] = true;
            }
        } else {
            for (int i = 1; i < 6 ; i++) {
                if (choiceInput.contains((Integer.toString(i)))) {
                    diceChoices[(i-1)] = true;
                }
            }
        }
        System.out.println(Arrays.toString(diceChoices));
        return diceChoices;
    }

    private boolean keepAllDice(boolean[] diceChoices) {
        for (int i = 0; i < 5; i++) {
            if (!diceChoices[i]) {
                return false;
            }
        }
        return true;
    }

    private void printDice(int[] diceHand) {
        System.out.println("Your dice are:");
        for (int i = 0; i < 5; i++) {
            System.out.print(diceHand[i] + ", ");
        }
        System.out.println();
    }


    private int chooseScorecardCategory() {
        while (true) {
            try {
                int categoryChoice = input.inputScoreChoice();
                if (scorecard.getScore(categoryChoice - 1).isEmpty()) {
                    return categoryChoice;
                }
            }
            catch(Exception e) {
                System.out.println("I'm sorry, that's not a valid score category. Please try again");
                }
        }
    }

    private void enterScore(int scoreChoice, int calculatedScore) {
        scorecard.setScore(scoreChoice, calculatedScore);
    }

    public int calculateFinalScore() {
        finalScore = scorecard.calculateFinalScore();
        System.out.println(playerName + ", you scored " + finalScore);
        return finalScore;
    }
}