import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScorecardTest {

    @Test
    @DisplayName("Calculate 1s score")
    void calculateScore() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {1, 2, 3, 6, 1};
        int scoreChoice = 1;
        int result = scorecard.calculateFinalScore(diceHand, scoreChoice);
        assertEquals(2, result);
    }

    @Test
    @DisplayName("Test low straight")
    void calculateLowStraightSuccess() {
        Scorecard scorecard = new Scorecard();
        int[] diceHand = {4, 2, 2, 5, 3};
        int scoreChoice = 10;
        int result = scorecard.calculateFinalScore(diceHand, scoreChoice);
        System.out.println(result);
        assertEquals(0, result);
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
    @DisplayName("Test Total Score with bonus")
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