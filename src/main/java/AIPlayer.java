import java.util.Arrays;

public class AIPlayer extends Player {

    public AIPlayer() {
        this.scorecard = new Scorecard();
        this.playerNumber = playerCount;
        playerCount ++;
        this.playerName = "Computer Player";

    }


    public void runAITurn() {
        int rolls = 1;
        boolean[] diceChoices = {false, false, false, false, false};
        int[] diceHand = {0, 0, 0, 0, 0};
        System.out.println("It's " + playerName + "'s turn");
        do {
            diceHand = diceRolls(diceChoices, diceHand);
            rolls ++;
        } while ((rolls < 3) & (!keepAllDice(diceChoices)));
        printDice(diceHand);
        int scoreChoice = aIChooseCategory(diceHand);
        System.out.println(scoreChoice);
        enterScore(scoreChoice, scorecard.calculateScore(diceHand, scoreChoice));
        System.out.println("Computer's Scorecard");
        scorecard.printScorecard();
    }

    public float[] calculateAllOdds(int[] diceHand) {
        float[] categoryOdds = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for(int i = 0; i < 13; i++) {
            if(scorecard.getScore(i).isEmpty()) {
                if (i < 6) {
                    categoryOdds[i] = calculateTopSectionOdds(diceHand, i);
                }

                if (i == 6 || i == 7) {
                    calculateKindOdds(diceHand, i);
                }

            }
        }

        return categoryOdds;
    }

    public float calculateTopSectionOdds(int[] diceHand, int category) {
        int diceCount = 0;

        for (int i = 0; i < 5; i++) {
            if (diceHand[i] == category) diceCount ++;
        }

        if (diceCount >= 3) return 1;
        if (diceCount == 2) return 0.421F;
        if (diceCount == 1) return 0.132F;
        if (diceCount == 0) return 0.035F;
        else return 0;

    }

    public float calculateKindOdds(int[] diceHand, int category) {
        int[] diceCount = giveDiceCount(diceHand);

        return 0;
    }

    public float calculateFullHouseOdds(int[] diceHand) {
        int[] diceCount = giveDiceCount(diceHand);


        return 0;
    }

    public float calculateStraightOdds(int[] diceHand, int category) {
        int[] diceCount = giveDiceCount(diceHand);


        return 0;
    }

    public float calculateYahtzeeOdds(int[] diceHand) {
        int[] diceCount = giveDiceCount(diceHand);
        int highestCount = findHighestDiceCount(diceCount);

        switch(highestCount){
            case 5: return 1;
            case 4: return 0.167F;
            case 3: return 0.028F;
            case 2: return 0.005F;
            case 1: return 0.001F;
        }

        return 0;
    }

    public int[] giveDiceCount(int[] diceHand) {
        int[] diceCount = {0, 0, 0, 0, 0, 0};

        for (int i = 0; i < 5; i ++ ){
            diceCount[diceHand[i] - 1] ++;
        }

        return diceCount;
    }

    public int findHighestDiceCount(int[] diceCount) {
        int highestCount = 0;
        int highestNumber = 0;
        for(int i = 0; i < 6; i++) {
            if(diceCount[i] > highestNumber) {
                highestCount = i;
                highestNumber = diceCount[i];
            }
        }

        return highestCount;
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
