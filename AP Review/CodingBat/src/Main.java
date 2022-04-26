public class Main {
  public static void main(String[] args) {
    Main cb = new Main();
    String[] a = {"a", "a", "b", "b"};
    String[] b = {"?", "c", "b", "?"};
    System.out.println(cb.scoreUp(a, b));
  }

  public int[] copyEvens(int[] nums, int count) {
    int[] result = new int[count];
    int j = 0;
    for (int i = 0; i < nums.length; i++) {
      // Check if the number is even and if it will fit in the result array
      if (nums[i] % 2 == 0 && j < count) {
        result[j] = nums[i];
        j++;
      }
    }
    return result;
  }

  public int[] copyEndy(int[] nums, int count) {
    int[] result = new int[count];
    int j = 0;
    for (int i = 0; i < nums.length; i++) {
      // Check if the number is endy and if it will fit in the result array
      if (isEndy(nums[i]) && j < count) {
        result[j] = nums[i];
        j++;
      }
    }
    return result;
  }

  private boolean isEndy(int num) {
    if (num >= 0 && num <= 10) {
      return true;
    }
    if (num >= 90 && num <= 100) {
      return true;
    }
    return false;
  }

  public int matchUp(String[] a, String[] b) {
    int count = 0;
    for (int i = 0; i < a.length; i++) {
      /*
      Count the number of times that the 2 strings are non-empty and start with the same char.
      The strings may be any length, including 0.
       */
      if (a[i].length() > 0 && b[i].length() > 0 && a[i].charAt(0) == b[i].charAt(0)) {
        count++;
      }
      if(a[i].charAt(0) == b[i].charAt(0)){
        count++;
      }
    }
    return count;
  }

  public int scoreUp(String[] key, String[] answers) {
    int score = 0;
    for (int i = 0; i < key.length; i++) {
      if (key[i].charAt(0) == answers[i].charAt(0)) {
        score += 4;
      }
      if(answers[i].charAt(0) != key[i].charAt(0) && answers[i].charAt(0) != '?') {
        score -= 1;
      }
    }
    return score;
  }
  public int commonTwo(String[] a, String[] b) {
    // Count how many strings are in both arrays and they are sorted in alphabetical order
    int count = 0;
    int i = 0;
    int j = 0;
    while(i < a.length && j < b.length) {
      if(a[i].compareTo(b[j]) < 0) { // If lexicographically smaller
        i++;
      } else if(a[i].compareTo(b[j]) > 0) { // If lexicographic larger than
        j++;
      } else {
        count++; // If equal
        i++;
        j++;
      }
    }
    return count;
  }
}
