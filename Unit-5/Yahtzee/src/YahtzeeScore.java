package src;


abstract class YahtzeeScore {

	abstract int calculateScore(int[] values);

	private int value = 0;
	private boolean used = false;
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


	public boolean isUsed() {
		return used;
	}

	public void setUsed() {
		this.used = true;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int[] values) {
		value = calculateScore(values);
	}
}
