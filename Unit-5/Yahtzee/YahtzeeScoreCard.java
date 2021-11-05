public class YahtzeeScoreCard {
	private YahtzeeScore[] scores = new YahtzeeScore[13];

	public YahtzeeScoreCard() {
		scores[0] = new OnesScore();
		scores[1] = new TwosScore();
		scores[2] = new ThreesScore();
		scores[3] = new FoursScore();
		scores[4] = new FivesScore();
		scores[5] = new SixesScore();
		scores[6] = new ThreeOfAKindScore();
		scores[7] = new FourOfAKindScore();
		scores[8] = new FullHouseScore();
		scores[9] = new SmallStraightScore();
		scores[10] = new LargeStraightScore();

	}

	public int getScore(int category, int[] dice) {
		return scores[category].getScore(dice);
	}


}


