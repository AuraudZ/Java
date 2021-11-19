package src;

public class SixesScore extends YahtzeeScore {


	public int calculateScore(int[] values) {
		int score = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] == 6) {
				score = score + 6;
			}
		}
		return score;
	}
}
