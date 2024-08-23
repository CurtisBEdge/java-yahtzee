import java.util.ArrayList;
import java.util.Arrays;

public class AIPlayer extends Player {

    public AIPlayer() {
        this.scorecard = new Scorecard();
        this.playerNumber = playerCount;
        playerCount ++;
        this.playerName = "Computer Player";

    }


    public void runAITurn() {
        int rolls = 0;
        boolean[] diceChoices = {false, false, false, false, false};
        int[] diceHand = {0, 0, 0, 0, 0};
        System.out.println("It's " + playerName + "'s turn");
        do {
            diceHand = diceRolls(diceChoices, diceHand);
            diceChoices = aIChooseDice(diceHand);
            rolls ++;
        } while ((rolls < 3) & (!keepAllDice(diceChoices)));
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

                if ((i == 6) || (i == 7)){
                    categoryOdds[i] = calculateKindOdds(diceCount, i);
                }

                if (i == 8) {
                    categoryOdds[i] = calculateFullHouseOdds(diceHand, diceCount);
                }

                if ((i == 9) || (i == 10)) {
                    categoryOdds[i] = calculateStraightOdds(diceHand, i);
                }

                if (i == 12) {
                    categoryOdds[i] = calculateYahtzeeOdds(diceCount);
                } 
                
            }
        }
        
        return categoryOdds;
    }


        public int calculateOddsPointsRatio(int[] diceHand, float[] categoryOdds) {
        float[] scores = {3, 6, 9, 12, 15, 18, 25, 25, 25, 30, 40, 50, scorecard.calculateDiceTotal(diceHand)};
        float highestRatio = -1;
        int highestScoreCategory = 0;
        
        for(int i = 0; i < 13; i++) {
            scores[i] = scores[i] * categoryOdds[i];
            if (scores[i] > highestRatio) {
                highestRatio = scores[i];
                highestScoreCategory = i;
            };
        }
        
        return highestScoreCategory;
    }

    public boolean[] chooseDiceToKeep(int[] diceHand, int chosenCategory){
        boolean[] diceChoices = {false, false, false, false, false};
        int[] diceCount = giveDiceCount(diceHand);
        int highestDiceCount = findHighestDiceCount(diceCount);
        int highestDiceCountNumber = findHighestDiceCountNumber(diceCount);
        
        if (chosenCategory < 6) { // Top section scores
            for(int i = 0; i < 5; i++) {
                if (diceHand[i] == chosenCategory + 1) {
                    diceChoices[i] = true;
                } 
            }
        }
        else if ((chosenCategory == 6) || (chosenCategory == 7)) { //3 & 4 of a kind scores
            for(int i = 0; i < 5; i++) {
                if(diceHand[i] == highestDiceCountNumber) {
                    diceChoices[i] = true;
                }
            }
        }
        else if (chosenCategory == 8) { //Full house
            for(int i = 0; i < 5; i++) {
                if(diceCount[diceHand[i] -1 ] > 1) diceChoices[i] = true;
            }
        }

        else if ((chosenCategory == 9) || (chosenCategory == 10)) {
            int chosenStraight;
            boolean probability = false;
            ArrayList<Integer> straightList = new ArrayList<>();

            if(chosenCategory == 9) chosenStraight = findMostLikelyLowStraight(diceHand, probability);
            else chosenStraight = findMostLikelyHighStraight(diceHand, probability);

            for(int i = 1; i < 7; i ++) {
                if(chosenCategory == 9) {
                    if((chosenStraight == 1) && (i < 5)) straightList.add(i);
                    if((chosenStraight == 2) && (i < 6) && (i > 1)) straightList.add(i);
                    if((chosenStraight == 3) && (i > 2)) straightList.add(i);
                }
                else {
                    if((chosenStraight == 1) && (i < 6)) straightList.add(i);
                    if((chosenStraight == 2) && (i > 1)) straightList.add(i);
                }
            
            }

            for(int j = 0; j < 5; j ++) {
                if(straightList.contains(diceHand[j])) {
                    diceChoices[j] = true;
                    Integer numberToBeRemoved = diceHand[j];
                    straightList.remove(numberToBeRemoved);
                }
            }
        }

        else if (chosenCategory == 11) { // Yahtzee scores **** ***** is the chosen category for Yahtzee right?
            for(int i = 0; i < 5; i++) {
                if (diceHand[i] == highestDiceCountNumber) {
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

    public float calculateKindOdds(int[] diceCount, int category) {
        int highestCount = findHighestDiceCount(diceCount);

        if (highestCount >= 4) return 1;
        if (category == 6) {
            if(highestCount == 3) return 1;
            if(highestCount == 2) return 0.421F;
            if(highestCount == 1) return 0.132F;
        }
        if (category == 7) {
            if(highestCount == 3) return 0.306F;
            if(highestCount == 2) return 0.069F;
            if(highestCount == 1) return 0.015F;
        }
        return 0;
    }

    public float calculateFullHouseOdds(int[] diceHand, int[]diceCount) {
        int[] diceCountTotals = {0, 0, 0, 0, 0};

        for (int i = 0; i < 6; i++) {
            if(diceCount[i] == 5) {
                return 1;
            }
            else if(diceCount[i] == 4) {
                return 0.167F;
            }
            else if (diceCount[i] == 3) {
                diceCountTotals[2] ++;
            }
            else if (diceCount[i] == 2) {
                diceCountTotals[1] ++;
            }
            else if (diceCount[i] == 1) {
                diceCountTotals[0] ++;
            }

        }

        if ((diceCountTotals[2] == 1) && (diceCountTotals[1] == 1)) return 1;
        if (diceCountTotals[1] == 2) return 0.333F;
        if ((diceCountTotals[1] == 1) && (diceCountTotals[0] == 3)) return 0.056F;
        if (diceCountTotals[0] == 5) return 0.039F;

        return 0;
    }

    public float calculateStraightOdds(int[] diceHand, int category) {

        int bestStraightDice = 0;
        boolean probability = true;

        if (category == 9) bestStraightDice = findMostLikelyLowStraight(diceHand, probability);
        else bestStraightDice = findMostLikelyHighStraight(diceHand, probability);

        if (bestStraightDice == 5) return 1;
        if (bestStraightDice == 4) {
           if (category == 10) return 0.167F;
            else return 1;
        }
        if (bestStraightDice == 3) {
            if (category == 10) return 0.028F;
            else return 0.167F;
        }
        if (bestStraightDice == 2) {
            if (category == 10) return 0.005F;
            else return 0.167F;
        }
        if (bestStraightDice == 1) {
            if (category == 10) return 0.001F;
            else return 0.005F;
        }

        return 0;

    }

    public float calculateYahtzeeOdds(int[] diceCount) {
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

    public int calculateHighestStreak (int[] diceCount) {
        int highestStreak = 0;

        for (int i = 0; i < 6; i++) {
            if (diceCount[i] > 0) highestStreak++;
            else highestStreak = 0;
        }

        return highestStreak;
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
        for(int i = 0; i < 6; i++) {
            if(diceCount[i] >= highestCount) {
                highestCount = diceCount[i];
            }
        }

        return highestCount;
    }

    public int findHighestDiceCountNumber(int[] diceCount) {
        int highestCount = 0;
        int highestNumber = 0;
        for(int i = 0; i < 6; i++) {
            if(diceCount[i] >= highestCount) {
                highestCount = diceCount[i];
                highestNumber = i + 1;
            }
        }

        return highestNumber; 
    }

    public int findMostLikelyLowStraight(int[] diceHand, boolean probability) {
        ArrayList<Integer> diceNumbers = new ArrayList<>();

        for(int i = 1; i <= 6; i++) diceNumbers.add(i);
        
        int straight1 = 0;
        int straight2 = 0;
        int straight3 = 0;



        for(int j = 0; j < 5; j++) {

            if(diceNumbers.contains(diceHand[j])) {
                Integer numberToBeRemoved = diceHand[j];
                diceNumbers.remove(numberToBeRemoved);
                if(diceHand[j] < 5) straight1 ++;
                if((diceHand[j] > 1) && (diceHand[j] < 6)) straight2 ++;
                if(diceHand[j] > 2) straight3 ++;
            }
        }

        if((straight3 >= straight2) && (straight3 >= straight1)) {
            if(probability) return straight3;
            else return 3;
            };
        if(straight2 >= straight1) {
            if(probability) return straight2;
            else return 2;
        }
        else 
            if(probability) return straight1;
            else return 1;
    }

    public int findMostLikelyHighStraight(int[] diceHand, boolean probability) {
        ArrayList<Integer> diceNumbers = new ArrayList<>();

        for(int i = 1; i <= 6; i++) diceNumbers.add(i);
        
        int straight1 = 0;
        int straight2 = 0;

        for(int j = 0; j < 5; j++) {
            if(diceNumbers.contains(diceHand[j])) {
                Integer numberToBeRemoved = diceHand[j];
                diceNumbers.remove(numberToBeRemoved);
                if(diceHand[j] < 6) straight1 ++;
                if(diceHand[j] > 1) straight2 ++;
            }
        }

        if(straight2 >= straight1) {
            if(probability) return straight2;
            else return 2;
        }
        else 
            if(probability) return straight1;
            else return 1;
    }



    public int aIChooseCategory (int[] diceHand) {
        //int[] potentialScores = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        //int highestScore = -1;
        //int highestScoreCategory = -1;

//        for(int i = 0; i < 13; i++ ) {
//            if(scorecard.getScore(i).isEmpty()) potentialScores[i] = scorecard.calculateScore(diceHand, i + 1);
//        }
//        System.out.println(Arrays.toString(potentialScores));
//        for(int i = 0; i < 13; i++ ) {
//            if(potentialScores[i] > highestScore) {
//                highestScoreCategory = i + 1;
//               highestScore = potentialScores[i];
//            }
//        }
//
//        return highestScoreCategory;

        int[] potentialScores = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        int chosenCategory = -1;
        int topSectionTotal = 0;
        int[] diceCountTotals = giveDiceCount(diceHand);
        int highestScore = -1;
        int highestScoreCategory = -1;

        for(int i = 0; i < 13; i++ ) {
            if(scorecard.getScore(i).isEmpty()) potentialScores[i] = scorecard.calculateScore(diceHand, i + 1);

            if((i < 6) && (potentialScores[i] > 0) topSectionTotal = topSectionTotal + potentialScores[i];
        }

        if(potentialScores[11] > 0) return 11; //chosing yahtzee
        if(potentialScores[10] > 0) return 10; //chosing high straight
        if(potentialScores[11] > 0) return 9; //chosing low straight
        if(potentialScores[9] > 0) {
            for(int j = 3; j < 6; ++) {
                if((diceCountTotals[j] == 3) && (potentialScores[j] == -1)) return 9;
            }
        }
        
        for(int k = 0; k < 6; k ++) {
            if((diceCountTotals[k] >= 3) && (potentialScores[k] > 0)) return k + 1; //returns the category number for the top section if there's 3 or more of a kind
            if((diceCountTotals[k] >= 4) && (potentialScores[7] > 0)) return 8; //returns the Four of a Kind category number if you have 4OAK and the top section number is already filled
            if((diceCountTotals[k] >= 3) && (potentialScores[6] > 0)) return 7; //returns the Three of a Kind category number if you have 3OAK and the top section number is already filled
        }

        // Working out where to put bad hands
        if(potentialScores[12] > 0) return 13;
        if(potentialScores[7] > -1) return 8;
        if(potentialScores[11] > -1) return 12;
        if(potentialScores[0] > -1) return 1;
        if(potentialScores[10] > -1) return 11;

        for(int i = 0; i < 13; i++ ) {
            if(potentialScores[i] > highestScore) {
                highestScoreCategory = i + 1;
               highestScore = potentialScores[i];
            }
        }
        
        return highestScoreCategory;
    }
}
