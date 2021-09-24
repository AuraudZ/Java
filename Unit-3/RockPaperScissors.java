public class RockPaperScissors {
    public static void main(String[] args) {
        System.out.println("Welcome to Rock, Paper, Scissors!");
        System.out.println("Best Of? ");
        int bestOf = TextIO.getlnInt();
        System.out.println("Please enter your choice: ");
        String input = TextIO.getlnString();
        Run(input, bestOf);
    }

    public static void Run(String input, int bestOf) {
        String youRockCompRock = "You         Comp\n\n __           __ \n/  \\         /  \\\n\\__/         \\__/";
        String youRockCompPaper = "You         Comp\n\n __            ___\n/  \\          /  /\n\\__/         /__/";
        String youRockCompScissors = "You         Comp\n\n __          O O\n/  \\          X\n\\__/         / \\";
        String youPaperCompRock = "You         Comp\n\n  ___          __ \n /  /         /  \\\n/__/          \\__/";
        String youPaperCompPaper = "You         Comp\n\n  ___           ___\n /  /          /  /\n/__/          /__/";
        String youPaperCompScissors = "You         Comp\n\n  ___         O O\n /  /          X\n/__/          / \\";
        String youScissorsCompRock = "You         Comp\n\nO O          __ \n X          /  \\\n/ \\         \\__/";
        String youScissorsCompPaper = "You         Comp\n\nO O           ___\n X           /  /\n/ \\         /__/";
        String youScissorsCompScissors = "You         Comp\n\nO O         O O\n X           X\n/ \\         / \\";
        String compChoiceString = "";
        int compChoice = (int) Math.random() * 3;
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
        boolean tie = false;
        int compWin = 0;
        int playerWin = 0;

        while (bestOf > 0) {
            if (input.equals("Rock")) {
                if (compChoiceString.equals("Rock")) {
                    tie = true;
                    System.out.println(youRockCompRock);
                } else if (compChoiceString.equals("Paper")) {
                    compWin++;
                    System.out.println(youRockCompPaper);
                    System.out.println("Score: " + compWin + " - " + playerWin);
                } else {
                    playerWin++;
                    System.out.println(youRockCompScissors);
                    System.out.println("Score: " + compWin + " - " + playerWin);
                }
            } else if (input.equals("Paper")) {
                if (compChoiceString.equals("Rock")) {
                    playerWin++;
                    System.out.println("You win!");
                    System.out.println(youPaperCompRock);
                    System.out.println("Score: " + compWin + " - " + playerWin);
                } else if (compChoiceString.equals("Paper")) {
                    System.out.println(youPaperCompPaper);
                    tie = true;
                    System.out.println("Tie!");
                } else {
                    System.out.println(youPaperCompScissors);
                    compWin++;
                    System.out.println("Score: " + compWin + " - " + playerWin);
                    System.out.println("You lose!");

                }
            } else if (input.equals("Scissors")) {
                if (compChoiceString.equals("Rock")) {
                    System.out.println(youScissorsCompRock);
                    compWin++;
                    System.out.println("You lose!");
                    System.out.println("Score: " + compWin + " - " + playerWin);
                } else if (compChoiceString.equals("Paper")) {
                    System.out.println(youScissorsCompPaper);
                    playerWin++;
                    System.out.println("Score: " + compWin + " - " + playerWin);

                    System.out.println("You win!");
                } else {
                    System.out.println(youScissorsCompScissors);
                    tie = true;
                    System.out.println("Score: " + compWin + " - " + playerWin);
                    System.out.println("Tie!");
                }
            } else {
                bestOf++;
                System.out.println("Invalid input");
            }
            bestOf--;
            if (compWin == 1 && bestOf == 2) {
                System.out.println("Computer won!");
            } else if (playerWin == 1 && bestOf == 2) {
                System.out.println("You won the round!");
            } else {

            }
            if (bestOf == 0) {
                System.out.println("Game over!");
            } else {
                System.out.println("Please enter your choice: ");
                input = TextIO.getlnString();
            }
        }
    }
}