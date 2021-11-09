package src;

public class FivesScore extends YahtzeeScore {



	@Override
	public int calculateScore(int[] values) {
		int score = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] == 5) {
				score += 5;
			}
		}
		return score;
	}
}

