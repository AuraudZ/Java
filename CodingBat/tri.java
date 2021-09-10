package CodingBat;

public class tri {
    public int triangle(int rows) {
        int sum = 0;
        for (int i = 1; i <= rows; i++) {
            sum += i;
        }
        return sum;
    }
}

