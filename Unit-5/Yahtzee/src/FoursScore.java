package src;

public class FoursScore extends YahtzeeScore {


	public int calculateScore(int[] values) {
		int score = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] == 4) {
				score = score + 4;
			}
		}
		return score;
	}
}
