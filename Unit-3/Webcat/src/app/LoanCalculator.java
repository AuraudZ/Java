package app;

public class LoanCalculator {
    public static void main(String[] args) {
        String table = createAmortizationTable(10000, 950, 0.12);
        System.out.println(table);
    }

    public static String createAmortizationTable(double loanAmount, double monthlyPayment, double annualInterestRate) {

        double monthlyInterestRate = annualInterestRate / 12;
        double interest = 0;
        double remainingAmount = loanAmount;
        double temp = 0;
        int months = 1;
        if (monthlyPayment < 0) {
            return "Not possible to pay off the loan.";
        }
        StringBuilder sb = new StringBuilder();
        if (check(remainingAmount, monthlyPayment, annualInterestRate)) {
            return "Not possible to pay off the loan.";
        }
        sb.append((String.format("Month     Interest        Payment         Remaining%n")));
        while (remainingAmount > 0) {
            interest = Math.round(monthlyInterestRate * remainingAmount * 100) / 100.0;
            remainingAmount = Math.round((remainingAmount + interest) * 100) / 100.0;
            temp = remainingAmount;
            remainingAmount = remainingAmount - monthlyPayment;
            if (remainingAmount <= 0) {
                monthlyPayment = temp;
            }
            if (remainingAmount < 0) {

                remainingAmount = 0;
            }
            sb.append(String.format("%-2d", months));
            sb.append("        ");
            sb.append(String.format("$%-15.2f", interest));
            sb.append(String.format("$%-15.2f", monthlyPayment));
            sb.append(String.format("$%.2f", remainingAmount));
            sb.append("\n");
            months++;
        }
        return sb.toString();
    }

    public static boolean check(double remainingAmount, double monthlyPayment, double annualInterestRate) {
        double monthlyInterestRate = annualInterestRate / 12;
        double interest = Math.round(monthlyInterestRate * remainingAmount * 100) / 100.0;
        if (monthlyPayment <= interest) {
            return true;
        }
        return false;
    }
}