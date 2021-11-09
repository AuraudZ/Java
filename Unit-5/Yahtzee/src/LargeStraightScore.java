package src;

import java.util.Arrays;

public class LargeStraightScore extends YahtzeeScore {

	public int calculateScore(int[] values) {
		int score = 0;
		Arrays.sort(values);
		for (int i = 0; i < values.length - 1; i++) {
			if (values[i] == values[i + 1] - 1) {
				score = values[i] * 5;
			}
		}
		return score;
	}
}
