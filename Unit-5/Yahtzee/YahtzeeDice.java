public class YahtzeeDice {
    private Die[] dice = new Die[5];

    private boolean[] isHeldArray = new boolean[5];

    public YahtzeeDice() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
        }
    }

    private YahtzeeDice(Die[] dice, int numSides) {
        this.dice = dice;
        for (int i = 0; i < this.dice.length; i++) {
            dice[i] = new Die(numSides);
        }
    }

    public YahtzeeDice(int numSides) {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die(numSides);
        }
    }

    public Die[] getDice() {
        return this.dice;
    }

    public void setDice(Die[] dice) {
        this.dice = dice;
    }

    public String toString() {
        return "dice = " + getDice();
    }

    public int[] getDiceValues() {
        int[] diceValues = new int[5];
        for (int i = 0; i < dice.length; i++) {
            diceValues[i] = dice[i].getCurrentValue();
        }
        return diceValues;
    }

    public int roll() {
        int sum = 0;
        for (int i = 0; i < dice.length; i++) {
            sum += dice[i].roll();
        }
        return sum;
    }

    public int roll(boolean[] isHeldArray) {
        int sum = 0;
        for (int i = 0; i < dice.length; i++) {
            if (isHeldArray[i] == false) {
                sum += dice[i].roll();
            } else {
                sum += dice[i].getCurrentValue();
            }
        }
        return sum;
    }
}
