package src;

public class OnesScore extends YahtzeeScore {


	public int calculateScore(int[] values) {
		int sum = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] == 1) {
				sum += 1;
			}
		}
		return sum;
	}
}
