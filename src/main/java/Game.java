import java.util.ArrayList;

public class Game {

    private final PlayerInput input;
    private Integer numberOfPlayers;

    private Integer numberOfAIPlayers;

    private ArrayList<Player> playerList;

    private Integer roundNumber;

    public Game() {
        this.roundNumber = 1;
        this.numberOfPlayers = 0;
        this.numberOfAIPlayers = 0;
        this.playerList = new ArrayList<>();
        this.input = new PlayerInput();
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers() {
        numberOfPlayers = input.setNumberOfPlayers();
    }

    public Integer getNumberOfAIPlayers() {
        return numberOfAIPlayers;
    }

    public void setNumberOfAIPlayers() {
        numberOfAIPlayers = input.setNumberOfAIPlayers();
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

        for (int i =0; i < numberOfAIPlayers; i++) {
            AIPlayer ai = new AIPlayer();
            playerList.add(ai);
        }
    }

    public void runRound() {
    //    for (int i = 0; i < playerList.size(); i++) {
    //       if (playerList[i] instanceof AIPlayer) {
    //
    //        }
    //    }
        int playerListSize = playerList.size();
        System.out.println(playerListSize);
        playerList.forEach(player -> {
            if (player instanceof AIPlayer) {
                System.out.println("AIPlayer");
                ((AIPlayer) player).runAITurn();}
            else {player.runHumanTurn();}
        });

        // playerList.forEach(Player::runHumanTurn);

    }

    public void calculateWinner() {
        int winningScore = 0;
        int winningPlayer = 5;

        for (int i = 0; i < playerList.size(); i++) {
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
            System.out.println("Congratulations " + playerList.get(winningPlayer).getPlayerName() + "! You Win!");
        }
        }
}