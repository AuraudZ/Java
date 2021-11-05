public class Ones extends Score {
    public Ones(int value) {
        super.setValue(this.value);
    }

    public String toString() {
        return "Ones: " + super.getValue();
    }

}

    @Override
    int calculateScore(int[] values) {
        // TODO Auto-generated method stub
        int sum = 0;
        for (int i = 0; i < dice.length; i++) {
            if (dice[i] == 1) {
                sum += 1;
            }
        }
        return sum;
    }
}
