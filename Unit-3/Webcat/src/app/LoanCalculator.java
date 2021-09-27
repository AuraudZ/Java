package app;

import java.text.NumberFormat;

public class LoanCalculator {
    public static void main(String[] args) {
        String table = createAmortizationTable(10000, 950, 0.17);
        System.out.println(table);
    }

    public static String createAmortizationTable(double loadAmount, double monthlyPayment, double annualInterestRate) {
        double monthlyInterestRate = annualInterestRate / 12;
        double payment = loadAmount;
        double monthlyInterestPayment = 0;
        double principalPaid = 0;
        int months = 12;
        StringBuilder sb = new StringBuilder();
        if (monthlyPayment < 0) {
            return "Monthly payment is negative";
        }

        if (monthlyPayment > loadAmount) {
            return "Monthly payment is greater than the load amount";
        }
        double paymentCheck = 0;

        sb.append(String.format("Month     Interest        Payment         Remaining%n"));
        for (int i = 1; i < months; i++) {
            // payment is static untill final month

            // principalPaid is the monthly payment
            if (principalPaid < 0) {
                return "Monthly payment is greater than the load amount";
            }

            // calculate interest
            monthlyInterestPayment = payment * monthlyInterestRate;
            // calculate principal paid
            principalPaid = monthlyPayment - monthlyInterestPayment;
            // calculate remaining balance

            // format and print
            sb.append(
                    String.format("%2d %15.2f %15.2f %15.2f%n", i + 1, monthlyInterestPayment, paymentCheck, payment));

        }
        return sb.toString();
    }
}