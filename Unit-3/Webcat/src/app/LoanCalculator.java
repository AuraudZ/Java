package app;

public class LoanCalculator {
    public static void main(String[] args) {
        String table = createAmortizationTable(10000, 950, 0.17);
        System.out.println(table);
    }

    public static String createAmortizationTable(double loadAmount, double monthlyPayment, double annualInterestRate) {
        double monthlyInterestRate = annualInterestRate / 12;
        String amoTable = "";
        double balance = loadAmount;
        double interestPaid = 0;
        double principalPaid = 0;
        int months = 12;
        StringBuilder sb = new StringBuilder();
        if (monthlyPayment < 0) {
            return "Monthly payment is negative";
        }

        if (monthlyPayment > loadAmount) {
            return "Monthly payment is greater than the load amount";
        }

        sb.append(String.format("Month     Interest        Payment         Remaining " + "\n"));
        sb.append(String.format("%1$-10s%2$-15s%3$-15s%4$-15s", "", "", "", "")).append("\n");
        for (int i = 0; i < months; i++) {
            double monthlyInterest = balance * monthlyInterestRate;
            double monthlyPrincipal = monthlyPayment - monthlyInterest;
            balance -= monthlyPrincipal;
            interestPaid += monthlyInterest;
            principalPaid += monthlyPrincipal;
            sb.append(String.format("%1$-10d%2$-15.2f%3$-15.2f%4$-15.2f", i + 1, monthlyInterest, monthlyPrincipal,
                    balance)).append("\n");
        }
        sb.append(String.format("%1$-10s%2$-15.2f%3$-15.2f%4$-15.2f", "Total", interestPaid, principalPaid, balance))
                .append("\n");
        return sb.toString();
    }
}