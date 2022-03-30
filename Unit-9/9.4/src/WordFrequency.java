
public class WordFrequency {
	
	private String word;
	private int count;
	
	public WordFrequency(String word) {
		this.word = word;
		count = 1;
	}
	
	public String getWord() {
		return word;
	}
	
	public int getCount() {
		return count;
	}
	
	public void increaseCount() {
		count++;
	}
	
	public boolean equals(Object obj) {
		if ( ! (obj instanceof WordFrequency)) {
			return false;
		}
		return ((WordFrequency)obj).word.equals(this.word);
	}
	
	
	
	public String toString() {
		return word + ": " + count;
	}

}
