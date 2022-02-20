import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SimpleTest {

    int[] longEvenRandomArray;
    int[] copyOflongEvenRandomArray;

    public static void main(String[] args) {

        SimpleTest test = new SimpleTest();
        test.longEvenRandomArray = readArrayFile("longEvenRandomArray.txt");
        test.copyOflongEvenRandomArray =
                Arrays.copyOf(test.longEvenRandomArray, test.longEvenRandomArray.length);
        int[] temp = test.copyOflongEvenRandomArray;
        System.out.println(temp.length);
    }


    public static int[] readArrayFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            ArrayList<Integer> nums = new ArrayList<Integer>();
            while (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                nums.add(n);
            }
            int[] numsArray = new int[nums.size()];
            for (int i = 0; i < numsArray.length; i++) {
                numsArray[i] = nums.get(i);
            }
            return numsArray;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
