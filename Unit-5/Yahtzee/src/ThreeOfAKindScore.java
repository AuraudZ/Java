package src;

import java.util.Arrays;

public class ThreeOfAKindScore extends YahtzeeScore {


	public int calculateScore(int[] values) {
		int score = 0;
		Arrays.sort(values);
		for (int i = 0; i < values.length - 2; i++) {
			if (values[i] == values[i + 1] && values[i] == values[i + 2]) {
				score = values[i] * 3;
			}
		}
		return score;
	}
}


