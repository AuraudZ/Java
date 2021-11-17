package src;


public class YahtzeeScoreCard {
	private YahtzeeScore[] scores = new YahtzeeScore[13];

	public YahtzeeScoreCard() {
		scores[0] = new OnesScore();
		scores[0].setName("Ones Score");
		scores[1] = new TwosScore();
		scores[1].setName("Twos Score");
		scores[2] = new ThreesScore();
		scores[2].setName("Threes Score");
		scores[3] = new FoursScore();
		scores[3].setName("Fours Score");
		scores[4] = new FivesScore();
		scores[4].setName("Fives Score");
		scores[5] = new SixesScore();
		scores[5].setName("Sixes Score");
		scores[6] = new ThreeOfAKindScore();
		scores[6].setName("Three of a Kind Score");
		scores[7] = new FourOfAKindScore();
		scores[7].setName("Four of a Kind Score");
		scores[8] = new FullHouseScore();
		scores[8].setName("Full House Score");
		scores[9] = new SmallStraightScore();
		scores[9].setName("Small Straight Score");
		scores[10] = new LargeStraightScore();
		scores[10].setName("Large Straight Score");
		scores[11] = new ChanceScore();
		scores[11].setName("Chance Score");
		scores[12] = new YahtzeesScoredScore();
		scores[12].setName("Yahtzee Score");


	}

	public YahtzeeScore[] getScores() {
		return scores;
	}

	public boolean gameOver() {
		for (int i = 0; i < scores.length; i++) {
			if (!scores[i].isUsed()) {
				return false;
			}
		}
		return true;
	}

	public String printScoreCard() {
		String str = "";
		for (int i = 0; i < scores.length; i++) {
			str = str + " " + scores[i].getName() + ":" + scores[i].getValue() + "\n";
		}
		return str;
	}

	public int getScore(int choice, int[] dice) {
		return scores[choice].calculateScore(dice);
	}


	public String printPotentialScoreCard(int[] dice) {
		String str = "";
		for (int i = 0; i < scores.length; i++) {
			if (!scores[i].isUsed()) {
				str = str + " " + scores[i].getName() + ":" + scores[i].calculateScore(dice) + "\n";
			} else {
				str = str + " " + scores[i].getName() + ":" + " LOCKED \n";
			}
		}
		return str;
	}

	public void setValue(int input, int[] diceValues) {
		scores[input].setValue(diceValues);
		scores[input].setUsed();

	}

	public int getValue(int input) {
		return scores[input].getValue();
	}

	public boolean isUsed(int input) {
		for (int i = 0; i < scores.length; i++) {
			if (scores[i].getName().equals(scores[input - 1].getName())) {
				return scores[i].isUsed();
			}
		}
		return false;
	}

	public int totalScore() {
		int sum = 0;
		for (int i = 0; i < scores.length; i++) {
			sum += scores[i].getValue();
		}
		return sum;
	}

}


