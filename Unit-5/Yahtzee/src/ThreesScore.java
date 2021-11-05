package src;

public class ThreesScore extends YahtzeeScore {

	public int calculateScore(int[] values) {
		int score = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] == 3) {
				score = score + 3;
			}
		}
		return score;
	}
}
