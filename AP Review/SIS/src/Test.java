public class Test extends Assignment {
	public Test(String name, String dateAssigned, int pointsPossible, int pointsEarned) {
		super(name, dateAssigned, pointsPossible, pointsEarned);
	}


	public void grade() {
		System.out.println("Graded!");
	}
	
}
