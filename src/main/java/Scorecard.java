import java.util.*;

public class Scorecard {

    private static String[] scoreCategory = {
            "1. Ones",
            "2. Twos",
            "3. Threes",
            "4. Fours",
            "5. Fives",
            "6. Sixes",
            "7. 3 of a Kind",
            "8. 4 of a Kind",
            "9. Full House",
            "10. Low Straight",
            "11. High Straight",
            "12. Yahtzee",
            "13. Chance"};

    private List<String> scorecard;

    public Scorecard() {
        List<String> scorecardList = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            scorecardList.add("");
        }
        this.scorecard = scorecardList;
    }

    public String getScoreCategory(int categoryChoice) {return scoreCategory[categoryChoice];}

    public void setScore(int scoreChoice, int score) {
        scorecard.set((scoreChoice - 1), Integer.toString(score));
    }

    public String getScore(int scoreCategory){
        return scorecard.get(scoreCategory);
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
        Arrays.sort(diceHand);
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

    public int calculateTopScores(int[] diceHand, int scoreChoice) {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (diceHand[i] == scoreChoice) {
                count ++;
            }
        }
        return count * scoreChoice;
    }

    public int calculateXOfAKind(int[] diceHand, int scoreChoice) {
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

    public int calculateFullHouse(int[] diceHand) {
        if (diceHand[0] == diceHand[4]) //Checks if all dice are equal
            return 25;
        if ((diceHand[0] == diceHand[1] && diceHand[2] == diceHand[4]) //Checks if Full House is pair & 3 of a kind
                || (diceHand[0] == diceHand[2] && diceHand[3] == diceHand[4]) ) //Checks if Full House is 3 of a kind & pair
            return 25;
        return 0;
    }

    public int calculateStraight(int[] diceHand, int scoreChoice) {
        int straightCount = 0;

        //Calculates the amount of consecutive numbers are in the hand
        for (int i =0; i < 4; i++){
            if (diceHand[i] == (diceHand[i + 1] - 1)) straightCount++;
            else if (diceHand[i] < (diceHand[i + 1] -1 )) straightCount = 0;
        }

        if ((scoreChoice == 10) && straightCount >= 3)
            return 30;
        else if ((scoreChoice == 11) && straightCount == 4)
            return 40;
        else
            return 0;

    }

    public int calculateYahtzee(int[] diceHand) {
        for (int i = 0; i < 4; i++)
            if(diceHand[i] != diceHand[i+1]) return 0;

        return 50;
    }

    public int calculateDiceTotal(int[] diceHand) {
        return Arrays.stream(diceHand).sum();
    }

    public int calculateFinalScore() {
        int totalScore = 0;
        for (int i = 0; i < 6; i++) {
            totalScore = totalScore + Integer.parseInt(scorecard.get(i));
        }
        if (totalScore >= 63) totalScore += 35;

        for (int j = 6; j < 13 ; j++) {
            totalScore += Integer.parseInt(scorecard.get(j));
        }
        return totalScore;
    }

}