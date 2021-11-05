package Yahtzee;

abstract class YahtzeeScore {
	abstract int calculateScore(int[] values);

	public int getScore(int[] values) {
		return calculateScore(values);
	}

}
