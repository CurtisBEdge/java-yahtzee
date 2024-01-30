import java.util.Scanner;

public class PlayerInput {

    private final Scanner sc = new Scanner(System.in);

    public PlayerInput()
    {}

    public int setNumberOfPlayers() {
        while (true) {
            System.out.println("How many players are there?");
            System.out.println("Enter a number between 1 and 4.");
            String input = sc.nextLine();
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
        String input;
        String acceptedCharacters = "0 1 2 3 4 5 6";
        boolean inputValid = true;
        while(true) {
            inputValid = true;
            System.out.println("Which would you like to keep? Enter 1 - 5, 6 to reroll all and 0 to keep all.");
            input = sc.nextLine();
            String[] validateInput = input.split(" ");
            for (String s : validateInput) {
                if (!acceptedCharacters.contains(s)) {
                    inputValid = false;
                    break;
                }
            }

            if (inputValid)
                return input;
            else {
                System.out.println("I'm sorry, that isn't a valid input.");
            }
        }
    }

    public int inputScoreChoice(){
        System.out.println("Enter the category number you wish to place your score.");
        return Integer.parseInt(sc.nextLine());
    }
}

