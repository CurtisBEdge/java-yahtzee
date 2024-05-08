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
            diceChoices = aIChooseDice(diceHand);
            rolls ++;
        } while ((rolls < 3) & (!keepAllDice(diceChoices)));
        printDice(diceHand);
        int scoreChoice = aIChooseCategory(diceHand);
        System.out.println(scoreChoice);
        enterScore(scoreChoice, scorecard.calculateScore(diceHand, scoreChoice));
        System.out.println("Computer's Scorecard");
        scorecard.printScorecard();
    }

    public boolean[] aIChooseDice(int[] diceHand) {
        float[] categoryOdds = calculateAllOdds(diceHand);
        int chosenCategory = calculateOddsPointsRatio(diceHand, categoryOdds);

        return chooseDiceToKeep(diceHand, chosenCategory);

    }

    public float[] calculateAllOdds(int[] diceHand) {
        float[] categoryOdds = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] diceCount = giveDiceCount(diceHand);
        
        for(int i = 0; i < 13; i++) {
            if(scorecard.getScore(i).isEmpty()) {
                if (i < 6) {
                    categoryOdds[i] = calculateTopSectionOdds(diceCount, i);
                }
                if (i == 12) {
                    categoryOdds[i] = calculateYahtzeeOdds(diceCount);
                } 
                
            }
        }
        
        return categoryOdds;
    }


        public int calculateOddsPointsRatio(int[] diceHand, float[] categoryOdds) {
        float[] scores = {3, 6, 9, 12, 15, 18, 25, 25, 25, 30, 40, scorecard.calculateDiceTotal()};
        float highestRatio = -1;
        
        for(int i = 0; i < 13; i++) {
            scores[i] = scores[i] * categoryOdds[i];
            if (scores[i] > highestRatio) {highestRatio = scores[i]};
        }
        
        return highestRatio;
    }

    public boolean[] chooseDiceToKeep(int[] diceHand, int chosenCategory){
        boolean[] diceChoices = {false, false, false, false, false};
        
        if (chosenCategory < 6) { // Top section scores
            for(int i = 0; i < 6; i++) {
                if (diceHand[i] == i + 1) {
                    diceChoices[i] = true;
                } 
            }
        }
        else if (chosenCategory == 11) { // Yahtzee scores
            int yahtzeeDiceNumber = findHighestDiceCount(diceHand)
            for(int i = 0; i < 5) {
                if (diceHand[i] == yahtzeeDiceNumber) {
                    diceChoices[i] = true;
                }
            }
        }
        
        return diceChoices;    
    }


    public float calculateTopSectionOdds(int[] diceCount, int category) {
        
        if (diceCount[category] >= 3) return 1;
        if (diceCount[category] == 2) return 0.421F;
        if (diceCount[category] == 1) return 0.132F;
        else return 0.035F;
        
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

    public float calculateYahtzeeOdds(int[] diceHand, int[] diceCount) {
        int highestCount = findHighestDiceCount(diceCount);
        
        switch(highestCount){
            case 5: return 1;
            case 4: return 0.167;
            case 3: return 0.028;
            case 2: return 0.005;
            case 1: return 0.001;
        }
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
