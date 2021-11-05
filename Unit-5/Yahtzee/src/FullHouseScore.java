package src;

import java.util.Arrays;

public class FullHouseScore extends YahtzeeScore {


	@Override
	public int calculateScore(int[] values) {
		int score = 0;
		Arrays.sort(values);
		if (values[0] == values[1] && values[1] == values[2] && values[3] == values[4]) {
			score = 25;
		} else if (values[0] == values[1] && values[2] == values[3] && values[3] == values[4]) {
			score = 25;
		}
		return score;
	}
}
