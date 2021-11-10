public class CustomTester {
    public static void main(String[] args) {
        CreditAccount a1 = new CreditAccount("Joy P. Sai-Chow", 1000, 0.12);
        System.out.println(a1.calculateMinimumMonthlyPayment());
        System.out.println(a1.toString());
    }
}
