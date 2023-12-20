import java.util.ArrayList;

public class Game {

    private final PlayerInput input;
    private Integer numberOfPlayers;

    private ArrayList<Player> playerList;

    private Integer roundNumber;

    public Game() {
        this.roundNumber = 1;
        this.numberOfPlayers = 0;
        this.playerList = new ArrayList<>();
        this.input = new PlayerInput();
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers() {
        numberOfPlayers = input.setNumberOfPlayers();
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber() {
        this.roundNumber ++;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayers() {
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player();
            String newPlayerName = input.inputName(player.getPlayerNumber());
            player.setPlayerName(newPlayerName);
            System.out.println("Welcome " + player.getPlayerName());
            playerList.add(player);
        }
    }

    public void runRound() {
        playerList.forEach(Player::runTurn);

    }

    public void calculateWinner() {
        int winningScore = 0;
        int winningPlayer = 5;

        for (int i = 0; i < playerList.size() - 1; i++) {
            int playerScore = playerList.get(i).calculateFinalScore();
            if(playerScore > winningScore) {
                winningScore = playerScore;
                winningPlayer = i;
                }
            else if(playerScore == winningScore) {
                winningPlayer = 5;
            }
        }

        if (winningPlayer == 5) {
            System.out.println("It's a Draw!");
            }
        else {
            System.out.println("Congratulations" + playerList.get(winningPlayer).getPlayerName() + "! You Win!");
        }
        }
}