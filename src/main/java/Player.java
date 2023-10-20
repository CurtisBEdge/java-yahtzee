import java.util.*;

public class Player {

    private Scanner sc = new Scanner(System.in);
    private static Integer playerCount = 1;

    private Integer playerNumber;

    private Scorecard scorecard;

    private String playerName;

    public Player() {
        this.scorecard = new Scorecard();
        this.playerNumber = playerCount;
        playerCount ++;
    }

    public Integer getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(Integer playerNumber) {
        this.playerNumber = playerNumber;
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName() {
        System.out.println("Please enter the name of Player " + getPlayerNumber());
        this.playerName = sc.nextLine();
    }

    public void runTurn() {
        int rolls = 1;
        boolean[] diceChoices = {false, false, false, false, false};
        int[] diceHand = {0, 0, 0, 0, 0};
        scorecard.printScorecard();
        do {
            diceHand = diceRolls(diceChoices, diceHand);
            System.out.println("Which would you like to keep? Enter 1 - 5, 6 to reroll all and 0 to keep all.");
            diceChoices = chooseDice();
            rolls ++;
            System.out.println(rolls);
            System.out.println(keepAllDice(diceChoices));
        } while ((rolls < 3) & (!keepAllDice(diceChoices)));
        scorecard.printScorecard();
        printDice(diceHand);
        int scoreChoice = 1;
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
        String choiceInput = sc.nextLine();
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

    private void enterScore(int scoreChoice, int calculatedScore) {
        scorecard.setScore(scoreChoice, calculatedScore);
    }
}