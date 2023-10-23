import java.util.Scanner;

public class PlayerInput {

    private Scanner sc = new Scanner(System.in);

    public PlayerInput()
    {

    }

    public int setNumberOfPlayers() {
        while (true) {
            System.out.println("How many players are there?");
            System.out.println("Enter a number between 1 and 4.");
            String input = sc.nextLine();
            System.out.println(input);
            if ((input.equals("1")) || (input.equals("2")) || (input.equals("3")) || (input.equals("4"))) {
                return Integer.parseInt(input);
            } else {
                System.out.println("I'm sorry, that's not a valid player number");
            }
        }
    }

    public String inputName(int playerNumber) {
        System.out.println("Please enter the name of Player " + playerNumber);
        return sc.nextLine();
    }

    public String inputDiceChoice(){
        System.out.println("Which would you like to keep? Enter 1 - 5, 6 to reroll all and 0 to keep all.");
        String input = null;
        String validCharacters = "1" + "2" + "3" + "4" + "5" + "6" + "0";
        while(true){
            input = sc.nextLine();
            if (input.matches(validCharacters)){
                return input;
            }
            else {
                System.out.println("I'm sorry, that isn't a valid input.");
            }
        }
    }
}
