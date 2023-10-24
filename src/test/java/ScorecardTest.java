import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScorecardTest {

    @Test
    @DisplayName("Calculate 1s score")
    void calculate1sScore() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {1, 2, 3, 6, 1};
        int scoreChoice = 1;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(2, result);
    }

    @Test
    @DisplayName("Calculate 6s score")
    void calculate6sScore() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {1, 6, 3, 6, 1};
        int scoreChoice = 6;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(12, result);
    }

    @Test
    @DisplayName("Calculate 6s score no 6s")
    void calculate6sScoreNo6s() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {1, 2, 3, 5, 1};
        int scoreChoice = 6;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(0, result);
    }


    @Test
    @DisplayName("Calculate 3 of a kind success")
    void calculate3OfAKindSuccess() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {1, 6, 3, 6, 6};
        int scoreChoice = 7;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(22, result);
    }

    @Test
    @DisplayName("Calculate 3 of a kind failure")
    void calculate3OfAKindFailure() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {1, 5, 3, 6, 6};
        int scoreChoice = 7;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Calculate 4 of a kind success")
    void calculate4OfAKindSuccess() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {6, 6, 3, 6, 6};
        int scoreChoice = 8;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(27, result);
    }

    @Test
    @DisplayName("Calculate 4 of a kind failure")
    void calculate4OfAKindFailure() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {1, 5, 3, 6, 6};
        int scoreChoice = 8;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Calculate full house success")
    void calculateFullHouseSuccess() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {3, 6, 3, 6, 6};
        int scoreChoice = 9;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(25, result);
    }

    @Test
    @DisplayName("Calculate full house failure")
    void calculateFullHouseFailure() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {1, 5, 3, 6, 6};
        int scoreChoice = 9;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Test low straight success")
    void calculateLowStraightSuccess() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {4, 2, 2, 5, 3};
        int scoreChoice = 10;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(30, result);
    }

    @Test
    @DisplayName("Test low straight failure")
    void calculateLowStraightFailure() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {4, 2, 1, 5, 6};
        int scoreChoice = 10;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Test high straight success")
    void calculateHighStraightSuccess() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {4, 2, 1, 5, 3};
        int scoreChoice = 11;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(40, result);
    }

    @Test
    @DisplayName("Test high straight failure")
    void calculateHighStraightFailure() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {6, 2, 1, 5, 3};
        int scoreChoice = 11;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Test yahtzee success")
    void calculateYahtzeeSuccess() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {5, 5, 5, 5, 5};
        int scoreChoice = 12;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(50, result);
    }

    @Test
    @DisplayName("Test yahtzee failure")
    void calculateYahtzeeFailure() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {5, 5, 2, 5, 5};
        int scoreChoice = 12;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Test chance")
    void calculateChance() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {1, 5, 2, 5, 1};
        int scoreChoice = 13;
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(14, result);
    }

    @Test
    @DisplayName("Test Total Score with bonus")
    void calculateTotalScoreWithBonus() {
        Scorecard scorecard = new Scorecard();
        int[] scores = {3,6,9,12,15,18,25,25,25,30,40,50,21};
        for (int i = 0; i < 13; i++) {
            scorecard.setScore(i+ 1, scores[i]);
        }
        int totalScore = scorecard.calculateFinalScore();
        assertEquals(314, totalScore);
    }

    @Test
    @DisplayName("Test Total Score without bonus")
    void calculateTotalScoreWithoutBonus() {
        Scorecard scorecard = new Scorecard();
        int[] scores = {3,6,6,12,15,18,25,25,25,30,40,50,21};
        for (int i = 0; i < 13; i++) {
            scorecard.setScore(i+ 1, scores[i]);
        }
        int totalScore = scorecard.calculateFinalScore();
        assertEquals(276, totalScore);
    }
}