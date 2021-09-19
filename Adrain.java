public class Adrain {
    public static void main(String[] args) {
        int input = TextIO.getlnInt();
        int max = 0;
        int maxNum = 0;
        for (int i = 1; i <= input; i++) {
            System.out.println("attempt #" + i);
            int count = 0;
            System.out.println("count is " + count);
            int num = i;
            while (num != 1) {
                if (num % 2 == 0) {
                    num = num / 2;
                } else {
                    num = 3 * num + 1;
                }
                count++;
            }
            if (count > max) {
                max = count;
                maxNum = i;
            }
        }
        System.out.println(max + " had the most terms.");

    }
}