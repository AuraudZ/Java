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
        // make sure the first line is lined up

        sb.append("Month").append("\t").append("Interest").append("\t").append("Principal").append("\t")
                .append("Balance").append("\n");

        if (monthlyPayment < 0) {
            return "Monthly payment is negative";
        }

        if (monthlyPayment > loadAmount) {
            return "Monthly payment is greater than the load amount";
        }

        for (int i = 1; i <= months; i++) {
            double interest = balance * monthlyInterestRate;
            double principal = monthlyPayment - interest;
            balance = balance - principal;
            interestPaid += interest;
            principalPaid += principal;
            sb.append(String.format("%d\t\t%.2f\t\t%.2f\t\t%.2f\n", i, interestPaid, principalPaid, balance));
        }

        sb.append("\nTotal paid: " + (interestPaid + principalPaid));
        sb.append("\nTotal interest paid: " + interestPaid);
        sb.append("\nTotal principal paid: " + principalPaid);
        sb.append("\nTotal balance: " + balance);
        amoTable = sb.toString();
        return amoTable;
    }
}
// for (int i = 1; i <= months; i++) {
// interestPaid = monthlyInterestRate * balance;
// principalPaid = monthlyPayment - interestPaid;
// balance = balance - principalPaid;
// sb.append(i + "\t\t" + interestPaid + "\t\t" + principalPaid + "\t\t" +
// balance + "\n");
// }
// amoTable = sb.toString();
// return amoTable;
// }}