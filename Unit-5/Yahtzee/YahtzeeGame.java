package Yahtzee;

public class YahtzeeGame {
	// Game level stuff:
	// score
	// dice
	// players

	private YahtzeeDice gameDice;
	private YahtzeeScoreCard gameScoreCard;

	public YahtzeeGame() {

		gameDice = new YahtzeeDice();
		gameScoreCard = new YahtzeeScoreCard();
	}

	public void startGame() {
		System.out.println("Hello, welcome to yahtzee!");
		while (!gameOver()) {
			playTurn();
		}
	}

	public void playTurn() {
		boolean[] isHeldArray = new boolean[5];
		for (int b = 0; b < isHeldArray.length; b++) {
			isHeldArray[b] = false;
		}
		for (int i = 0; i < 3; i++) {
			gameDice.roll(isHeldArray);
			System.out.println(gameDice.toString());
			if (i < 2) {
				System.out.println("Do you want to roll again");
				boolean t = TextIO.getlnBoolean();
				if (t) {
					for (int j = 0; j < isHeldArray.length; j++) {
						System.out.println("Would you like to hold dice " + (j + 1));
						isHeldArray[j] = TextIO.getlnBoolean();
					}

				} else {
					i = 3;
				}

			}


		}
		System.out.println("What would you like to score this as?");
		int choice = TextIO.getlnInt();
		int score = gameScoreCard.getScore(choice, gameDice.getDiceValues());
		System.out.println("Your score is " + score);
	}

	public boolean gameOver() {
		return false;
	}
}
