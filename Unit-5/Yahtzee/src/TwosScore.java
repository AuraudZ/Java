package src;

public class TwosScore extends YahtzeeScore {
	@Override
	public int calculateScore(int[] values) {
		int sum = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] == 2) {
				sum += 2;
			}
		}
		return sum;
	}
}
