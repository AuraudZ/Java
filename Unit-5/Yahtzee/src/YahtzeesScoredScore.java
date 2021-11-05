package src;

public class YahtzeesScoredScore extends YahtzeeScore {
    public int calculateScore(int[] dice) {
        int score = 0;
        for (int i = 0; i < dice.length; i++) {
            score += dice[i];
        }
        return score;
    }
}
