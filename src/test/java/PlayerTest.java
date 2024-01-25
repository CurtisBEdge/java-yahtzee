import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;


class PlayerTest {

    @Test
    void diceRollsReRollsAllDice() {
        Player player = new Player();
        boolean[] diceChoices = {false, false, false, false, false};
        int[] diceHand = {0, 0, 0, 0, 0};
        int[] newDice = player.diceRolls(diceChoices, diceHand);
        boolean anyZeros = false;
        for (int j : newDice) {
            if (j == 0) {
                anyZeros = true;
                break;
            }
        }
        assert (!anyZeros);
    }


}