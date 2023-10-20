import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Scanner sc = new Scanner(System.in);
    private Integer numberOfPlayers;

    private ArrayList<Player> players;

    private Integer roundNumber;

    public Game() {
        this.roundNumber = 1;
        this.numberOfPlayers = 0;
        this.players = new ArrayList<>();
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers() {
        do {
            System.out.println("How many players are there?");
            System.out.println("Enter a number between 1 and 4.");
            String input = sc.nextLine();
            if ((input.equals("1")) || (input.equals("2")) || (input.equals("3")) || (input.equals("4"))) {
                this.numberOfPlayers = Integer.parseInt(input);
            } else {
                System.out.println("I'm sorry, that's not a valid player number");
            }
        } while (this.numberOfPlayers == 0);
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber() {
        this.roundNumber ++;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers() {
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player();
            player.setPlayerName();
            System.out.println("Welcome " + player.getPlayerName());
            players.add(player);
        }
    }

    public void runRound() {
        players.forEach(Player::runTurn);

    }

}