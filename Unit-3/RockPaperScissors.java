public class RockPaperScissors {
    public static void main(String[] args) {
        System.out.println("Welcome to Rock, Paper, Scissors!");
        System.out.println("Please enter your choice: ");
        String input = TextIO.getlnString();
        Run(input);
    }

    // Get input from user
    // Get computor choice
    // Compare the differnce
    // Print who wins
    // Run loop to see who would win after best of 3
    public static void Run(String input) {
        String compChoiceString = "";
        int compChoice = (int) Math.random() * 3;
        System.out.println("Computer chose: " + compChoice);
        switch (compChoice) {
            case 0:
                compChoiceString = "Rock";
                break;
            case 1:
                compChoiceString = "Paper";
                break;
            case 2:
                compChoiceString = "Scissors";
                break;
        }
        System.out.println("Computer chose: " + compChoiceString);
        int bestOfThree = 3;
        boolean tie = false;
        int compWin = 0;
        int playerWin = 0;

        while (bestOfThree > 0) {
            if (input.equals("Rock")) {
                if (compChoiceString.equals("Rock")) {
                    tie = true;
                    System.out.println("Tie!");
                } else if (compChoiceString.equals("Paper")) {
                    compWin++;
                    System.out.println("You lose!");
                } else {
                    playerWin++;
                    System.out.println("You win!");
                }
            } else if (input.equals("Paper")) {
                if (compChoiceString.equals("Rock")) {
                    playerWin++;
                    System.out.println("You win!");
                } else if (compChoiceString.equals("Paper")) {
                    tie = true;
                    System.out.println("Tie!");
                } else {
                    compWin++;
                    System.out.println("You lose!");

                }
            } else if (input.equals("Scissors")) {
                if (compChoiceString.equals("Rock")) {
                    compWin++;
                    System.out.println("You lose!");
                } else if (compChoiceString.equals("Paper")) {
                    playerWin++;
                    System.out.println("You win!");
                } else {
                    tie = true;
                    System.out.println("Tie!");
                }
            } else {
                bestOfThree++;
                System.out.println("Invalid input");
            }
            // Who won take that and subtract from best of three
            bestOfThree--;
            // System.out.println("Best of three: " + bestOfThree);
            // if computor won 2 out of 3 ouput that computer won
            if (compWin == 1 && bestOfThree == 2) {
                System.out.println("Computer won!");
            } else if (playerWin == 1 && bestOfThree == 2) {
                System.out.println("You won the round!");
            }
            if (bestOfThree == 0) {
                System.out.println("Game over!");
            } else {
                System.out.println("Please enter your choice: ");
                input = TextIO.getlnString();
            }
        }
    }
}