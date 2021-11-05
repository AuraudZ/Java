package Yahtzee;

public class LargeStraightScore extends YahtzeeScore {



	public int calculateScore(int[] values) {
		int score = 0;
		if (values[0] == 1 && values[1] == 2 && values[2] == 3 && values[3] == 4) {
			score = 40;
		}
		return score;
	}
}
