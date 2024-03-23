public class Main {
    private static final Game game = new Game();


    public static void main(String[] args) {
        System.out.println("Hello and welcome to Yahtzee!");
        game.setNumberOfPlayers();
        game.setNumberOfAIPlayers();
        game.setPlayers();
        do {
            System.out.println("Round " + game.getRoundNumber());
            game.runRound();
            game.setRoundNumber();
        } while (game.getRoundNumber() <= 13);
        game.calculateWinner();

    }
    // you're up to game.calculateWinner. Need to handle a draw.
    //Found bug where if you enter an incorrect response for where to put dice, it gets stuck in a loop
    //More Tests!
}
