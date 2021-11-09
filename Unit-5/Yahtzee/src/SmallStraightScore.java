package src;

import java.util.Arrays;

public class SmallStraightScore extends YahtzeeScore {


	@Override
	public int calculateScore(int[] values) {
		int score = 0;
		// Small staight is a sequence of 4 numbers
		Arrays.sort(values);
		for (int i = 0; i < values.length - 3; i++) {
			if (values[i] + 1 == values[i + 1] && values[i] + 2 == values[i + 2]
					&& values[i] + 3 == values[i + 3]) {
				score = 15;
			}
		}
		return score;
	}
}
