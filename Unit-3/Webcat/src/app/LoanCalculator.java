package app;

import java.text.NumberFormat;

public class LoanCalculator {
    public static void main(String[] args) {
        String table = createAmortizationTable(10000, 950, 0.17);
        System.out.println(table);
    }

    public static String createAmortizationTable(double loadAmount, double monthlyPayment, double annualInterestRate) {
        double monthlyInterestRate = annualInterestRate / 12;
        double payment = 0;
        double interest = 0;
        double remainingAmount = 0;
        double principalPaid = 0;
        int months = 1;
        StringBuilder sb = new StringBuilder();
        if (monthlyPayment <= 0) {
            return "Monthly payment is negative";
        }
        payment = monthlyPayment;
        if (monthlyPayment > loadAmount) {
            return "Monthly payment is greater than the load amount";
        }
        sb.append(String.format("Month     Interest        Payment         Remaining%n"));

        while (loadAmount > 0) {
            remainingAmount = Math.round((loadAmount - payment) * 100) / 100.0;
            interest = Math.round(monthlyInterestRate * remainingAmount * 100) / 100.0;
            payment = Math.round(monthlyPayment * 100) / 100.0;
            sb.append(String.format("%2d %15.2f %15.2f %15.2f%n", months, interest, payment, remainingAmount));
            months++;
        }
        return sb.toString();
    }
}