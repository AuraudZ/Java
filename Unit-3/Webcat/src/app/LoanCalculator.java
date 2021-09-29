package app;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class LoanCalculator {
    public static void main(String[] args) {
        String table = createAmortizationTable(10000, 950, 0.17);
        System.out.println(table);
    }

    public static String createAmortizationTable(double loadAmount, double monthlyPayment, double annualInterestRate) {

        double monthlyInterestRate = annualInterestRate / 12;
        double interest = 0;
        double remainingAmount = loadAmount;
        double temp = 0;
        int months = 1;
        // is not possible to pay off loan
        if (monthlyPayment > loadAmount) {
            return "Not possible to pay off the loan.";
        }
        String format = "";

        StringBuilder sb = new StringBuilder();
        format = format.concat(String.format("Month     Interest        Payment         Remaining%n"));
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
            sb.append(String.format("$%-15s", interest));
            sb.append(String.format("$%-15.2f", monthlyPayment));
            sb.append(String.format("$%-14.2f", remainingAmount));
            sb.append("\n");
            months++;
        }
        return format + sb.toString();
    }
}