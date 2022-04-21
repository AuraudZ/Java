public class Homework extends Assignment {
	// this field is unique for Homework assignments only
	private boolean isExtraCredit = false; // not necessary, default is false

	
	public Homework(String name, String dateAssigned, int pointsPossible, int pointsEarned, boolean isExtraCredit) {
		super(name, dateAssigned, pointsPossible, pointsEarned);
		this.isExtraCredit = isExtraCredit;
	}

	public void grade() {
		if(isExtraCredit) {
			System.out.println("Extra credit added!");
		}
		System.out.println("Graded!");
	}
	
	
	
}
