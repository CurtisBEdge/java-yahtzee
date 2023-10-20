import java.util.*;

public class Scorecard {

    private static String[] scoreCategory = {
            "Ones",
            "Twos",
            "Threes",
            "Fours",
            "Fives",
            "Sixes",
            "3 of a Kind",
            "4 of a Kind",
            "Full House",
            "Low Straight",
            "High Straight",
            "Yahtzee",
            "Chance"};

    private List<String> scorecard;

    public Scorecard() {
        List<String> scorecardList = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            scorecardList.add("");
        }
        this.scorecard = scorecardList;
    }

    public void setScore(int scoreChoice, int score) {
        scorecard.set((scoreChoice - 1), Integer.toString(score));
    }

    public void printScorecard() {
        for (int i = 0; i < scoreCategory.length; i++) {
            if (i == 6) {
                System.out.println("----------------------");
            }
            int space = 22 - (scoreCategory[i].length()) - (scorecard.get(i).length()) ;
            System.out.print(scoreCategory[i]);
            for (int j = 0; j < space; j++) {
                System.out.print(" ");
            }
            System.out.print(scorecard.get(i) + "\n");
        }
        System.out.println();
    }

    public int calculateScore(int[] diceHand, int scoreChoice) {
        if ((scoreChoice >= 1) && (scoreChoice <= 6)) {
            return calculateTopScores(diceHand, scoreChoice);
        } else if ((scoreChoice >= 7) && (scoreChoice <= 8)) {
            return calculateXOfAKind(diceHand, scoreChoice);
        } else if (scoreChoice == 9) {
            return calculateFullHouse(diceHand);
        } else if ((scoreChoice >= 10) && (scoreChoice <= 11)) {
            return calculateStraight(diceHand, scoreChoice);
        } else if (scoreChoice == 12) {
            return calculateYahtzee(diceHand);
        } else {
            return calculateDiceTotal(diceHand);
        }
    }

    private int calculateTopScores(int[] diceHand, int scoreChoice) {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (diceHand[i] == scoreChoice) {
                count ++;
            }
        }
        return count * scoreChoice;
    }

    private int calculateXOfAKind(int[] diceHand, int scoreChoice) {
        for (int i = 1; i < 7; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                if (diceHand[j] == i) {
                    count ++;
                }
            }
            if (count >= 4)  {
                return calculateDiceTotal(diceHand);
            } else if ((count == 3) && (scoreChoice == 7)) {
                return calculateDiceTotal(diceHand);
            }
        }
        return 0;
    }

    private int calculateFullHouse(int[] diceHand) {
        return 0;
    }

    private int calculateStraight(int[] diceHand, int scoreChoice) {
        return 0;
    }

    private int calculateYahtzee(int[] diceHand) {
        for (int i = 1; i < 7 ; i++) {
            int count = 0;
            for (int j = 0; j < 6; j++) {
                if (diceHand[j] == i) {
                    count ++;
                }
            }
            if (count == 5) {
                return 50;
            }
        }
        return 0;
    }

    private int calculateDiceTotal(int[] diceHand) {
        return Arrays.stream(diceHand).sum();
    }

}