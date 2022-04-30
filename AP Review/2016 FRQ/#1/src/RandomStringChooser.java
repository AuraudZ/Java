public class RandomStringChooser {
    private String[] words;
    public RandomStringChooser(String[] words) {
        this.words= words;
    }

    public String getNext() {
      String[] availableWords = words;
      int randomIndex = (int) (Math.random() * availableWords.length);
      for(int i = 0; i < availableWords.length; i++) {
        if(i == randomIndex) {
          return availableWords[i];
        }
      }

    }

    public static void main(String[] args) {
        String[] wordArray = {"wheels", "on", "the", "bus"};
        RandomStringChooser randomStringChooser = new RandomStringChooser(wordArray);
        for (int i = 0; i < 10; i++) {
            System.out.println(randomStringChooser.getNext());
        }
    }
}
