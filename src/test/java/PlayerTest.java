import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class PlayerTest {

    @Mock
    private PlayerInput playerInputMock;

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

    @Test
    void chooseDiceRerollAll() {
        Player player = new Player();
        String fakeInput = "1 3 5 0";
        boolean[] expectedChoices = {true, true, true, true, true};
        when(playerInputMock.inputDiceChoice()).thenReturn(fakeInput);
        boolean[] diceChoices = player.chooseDice();
        assertEquals(expectedChoices, diceChoices);



    }

}