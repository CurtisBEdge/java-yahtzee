public class Main {
    private static final Game game = new Game();


    public static void main(String[] args) {
        System.out.println("Hello and welcome to Yahtzee!");
        game.setNumberOfPlayers();
        game.setPlayers();
        do {
            game.runRound();
            game.setRoundNumber();
            System.out.println(game.getRoundNumber());
        } while (game.getRoundNumber() <= 13);

    }
    // you're up to game.calculateWinner. Need to handle a draw.
    //Notes - need to sort validation on which dice in a hand to keep. Currently, allows input of random characters.
    //More Tests!
}
