
public class GuardianTroll {
    public static void main(String[] args) {
        // Declare variables
        String name;
        String favoriteColor;
        int favoriteNumber;
        // Get input from user
        System.out.print("What is your name? ");
        name = TextIO.getln();
        System.out.print("What is your favorite color? ");
        favoriteColor = TextIO.getln();
        favoriteColor = favoriteColor.toLowerCase();
        System.out.print("What is your favorite number? ");
        favoriteNumber = TextIO.getInt();
        if (favoriteNumber < 0 || favoriteNumber > 100) {
            System.out.println("You are not worthy " + name);
        } else if (favoriteColor.equals("green")) {
            System.out.println("You" + name + "is not worthy");
        } else {
            System.out.println("You may pass " + name);
        }

    }
}
