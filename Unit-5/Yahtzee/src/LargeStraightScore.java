package src;

import java.util.Arrays;

public class LargeStraightScore extends YahtzeeScore {

	public int calculateScore(int[] values) {
		int score = 0;
		Arrays.sort(values);
		if (values[0] == 1 && values[1] == 2 && values[2] == 3 && values[3] == 4) {
			score = 40;
		} else if (values[0] == 2 && values[1] == 3 && values[2] == 4 && values[3] == 5) {
			score = 40;
		} else if (values[0] == 3 && values[1] == 4 && values[2] == 5 && values[3] == 6) {
			score = 40;
		}
		return score;
	}
}
