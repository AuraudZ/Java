import java.util.Arrays;

public class Fix34 {
    public int[] fix34(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 3) {
                for (int j = i; j < nums.length; j++) {
                    if (nums[j] == 4) {
                        int temp = nums[i + 1];
                        nums[i] = 4;
                        nums[j] = temp;
                        break;
                    }

                }
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        Fix34 f = new Fix34();
        int[] nums = {5, 3, 5, 4, 5, 4, 5, 4, 3, 5, 3, 5};
        int[] result = f.fix34(nums);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
