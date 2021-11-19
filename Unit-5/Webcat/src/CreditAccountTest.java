import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CreditAccountTest {

	CreditAccount a1;
	CreditAccount a2;
	RewardsCreditAccount a3;
	RewardsCreditAccount a4;

	@Before
	public void setUp() throws Exception {
		a1 = new CreditAccount("Joy P. Sai-Chow", 1000, 0.12);
		a2 = new CreditAccount("Ke$ha", 10000, 0.12);
	}

	@Test
	public void testConstructorThrowsIllegalArgumentException() {
		try {
			CreditAccount impossibleAccount = new CreditAccount("neg", 1000, -0.25);
			fail("Constructor should have thrown an IllegalArgumentException.");
		} catch (IllegalArgumentException expectedArgument) {

		}
	}

	@Test
	public void testCalculateMinimumMonthlyPayment() {
		CreditAccount lowBalanceAccount = new CreditAccount("Mike", 12.35, 0.12);
		assertEquals(12.35, lowBalanceAccount.calculateMinimumMonthlyPayment(), 0);
		assertEquals(25.0, a1.calculateMinimumMonthlyPayment(), 0);
		assertEquals(200.0, a2.calculateMinimumMonthlyPayment(), 0);

	}

	@Test
	public void testHowManyMonthsUsingConstantPayment() {
		assertEquals(58, a2.howManyMonthsUsingConstantPayment(230.0));
	}

	@Test
	public void testHowManyMonthUsingConstantPaymentEqualToBalance() {
		assertEquals(2, a2.howManyMonthsUsingConstantPayment(a2.getBalance()));

	}

	@Test
	public void testMakeCharge() {
		a2.makeCharge(9728.74);
		assertEquals(19728.74, a2.getBalance(), 0.00000001);
	}

	@Test
	public void testMakePayment() {
		a2.makePayment(9728.74);
		assertEquals(271.26, a2.getBalance(), 0.00000001);
	}

	@Test
	public void testToString() {
		a2 = new CreditAccount("Ke$ha", 10000, 0.123);
		assertEquals("Ke$ha, $10000.00, 12.3%", a2.toString());
	}

	@Test
	public void testGetBalance() {
		assertEquals(10000, a2.getBalance(), 0);
	}


	@Test
	public void testGetInterestRate() {
		assertEquals(0.12, a2.getInterestRate(), 0);
	}

	@Test
	public void testSetInterestRate() {
		a1.setInterestRate(5.67);
		assertEquals(5.67, a1.getInterestRate(), 0);
	}

	@Test
	public void testSetInterestRateIllegalArgumentException() {
		try {
			a1.setInterestRate(-0.15);
			fail("Should have thrown IllegalArgumentException in setInterestRate.");
		} catch (IllegalArgumentException expectedException) {

		}
	}

	@Test
	public void testGetAccountHolder() {
		assertEquals("Ke$ha", a2.getAccountHolder());
	}

	@Test
	public void testSetAccountHolder() {
		a1.setAccountHolder("me");
		assertEquals("me", a1.getAccountHolder());
	}

	@Test
	public void testBothRewardsCreditAccountConstructors() {
		a3 = new RewardsCreditAccount("PsY", 1435.73, .24);
		a4 = new RewardsCreditAccount("Ziggy Top", 64.35, .12, 400);
	}

	@Test
	public void testMakeChargeRewards() {
		a3 = new RewardsCreditAccount("PsY", 1435.73, .24);
		a4 = new RewardsCreditAccount("Ziggy Top", 64.35, .12, 400);
		a3.makeCharge(45);
		a4.makeCharge(49.99);
		assertEquals(45, a3.getPoints());
		assertEquals(449, a4.getPoints());
	}

	@Test
	public void testToStringRewards() {
		a4 = new RewardsCreditAccount("Ziggy Top?##", 64.3, .123, 432);
		assertEquals("Ziggy Top?##, $64.30, 12.3%, 432 points", a4.toString());
	}

}
