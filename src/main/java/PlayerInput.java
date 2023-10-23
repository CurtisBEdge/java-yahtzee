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

    //need to add input validation for this method. Needs to fail if the input contains any characters that aren't 1, 2, 3, 4, 5, 6 or 0.
    //could make a list of the input, then iterate through it to make sure all characters are correct, then return the string.
    public String inputDiceChoice(){
        System.out.println("Which would you like to keep? Enter 1 - 5, 6 to reroll all and 0 to keep all.");
        String input = null;
        while(true){
            input = sc.nextLine();
            return input;
            //System.out.println("I'm sorry, that isn't a valid input.");
        }
    }

    public int inputScoreChoice(){
        System.out.println("Enter the category number you wish to place your score.");
        return Integer.parseInt(sc.nextLine());
    }
}

