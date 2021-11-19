// The assignment for this week is to implement the missing methods
// of two classes: CreditAccount and RewardsCreditAccount.
// DO NOT REMOVE THE private DESIGNATION OF ANY OF THE MEMBER VARIABLES!!!

public class RewardsCreditAccount extends CreditAccount {
	private int points; // For every dollar spent (rounded down),
	// the credit account gets a point

	// For creating a new RewardsCreditAccount object, starting with points
	public RewardsCreditAccount(String accountHolder, double balance, double interestRate,
			int points) {
		// this constructor should initialize all of the variables
		// with the given values. Don't change the names of the parameters!
		super(accountHolder, balance, interestRate);
		this.points = points;
	}

	// For creating a new RewardsCreditAccount object, starting with 0 points
	public RewardsCreditAccount(String accountHolder, double balance, double interestRate) {
		// this constructor should initialize points to zero
		// (in addition to initializing the other variables)
		// This can be (and should be) accomplished in one line of code!
		this(accountHolder, balance, interestRate, 0);
	}



	public void makeCharge(double amount) {
		// this method should increase the balance and
		// also award the correct amount of points
		super.makeCharge(amount);
		points += (int) Math.floor(amount);
	}

	public String toString() {
		// This method should return a String like the toString method
		// in CreditAccount, but with an added
		// comma and a space before appending the points
		// For example, an account with "Joe F. Pyne" as the accountHolder,
		// 7384.28 as the balance, 0.173 as the interest rate,
		// and 567 points should return the String
		// "Joe F. Pyne, $7384.28, 17.3%, 567 points"
		// Make this method as short and succinct as possible!
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(", " + points + " points");
		return sb.toString();
	}

	public int getPoints() {
		return points;
	}
}
