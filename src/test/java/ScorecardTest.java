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
        int result = scorecard.calculateScore(diceHand, scoreChoice);
        assertEquals(2, result);
    }
}