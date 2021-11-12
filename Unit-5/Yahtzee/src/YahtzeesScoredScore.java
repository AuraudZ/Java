package src;

import java.util.Arrays;

public class YahtzeesScoredScore extends YahtzeeScore {

    /**
     * @param values
     * @return int
     */
    public int calculateScore(int[] values) {
        int score = 0;
        Arrays.sort(values);
        for (int i = 0; i < values.length; i++) {
            if (values[i] == values[values.length - 1]) {
                score = values[i] * values.length;
            }
        }
        return score;
    }
}
