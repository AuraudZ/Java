package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoanCalculatorTest {

	@Test
	public void testImpossibleLoanRepayment100000Percent12Payment100() {
		assertTrue(LoanCalculator.createAmortizationTable(100000, 100, 0.12).indexOf("ot possible") >= 0);
	}

	@Test
	public void testLoanOf10000Percent17Payment950TestAllValuesForPerfectFormatting() {
		assertEquals(
				"Month     Interest        Payment         Remaining\n1         $141.67         $950.00         $9191.67\n2         $130.22         $950.00         $8371.89\n3         $118.60         $950.00         $7540.49\n4         $106.82         $950.00         $6697.31\n5         $94.88          $950.00         $5842.19\n6         $82.76          $950.00         $4974.95\n7         $70.48          $950.00         $4095.43\n8         $58.02          $950.00         $3203.45\n9         $45.38          $950.00         $2298.83\n10        $32.57          $950.00         $1381.40\n11        $19.57          $950.00         $450.97\n12        $6.39           $457.36         $0.00\n",
				LoanCalculator.createAmortizationTable(10000, 950, 0.17));
	}

	@Test
	public void testHeaderGeneratedProperly() {
		assertTrue(LoanCalculator.createAmortizationTable(10000, 950, 0.17)
				.indexOf("Month     Interest        Payment         Remaining") >= 0);
	}

	@Test
	public void testLoanOf10000Percent17Payment950FirstMonthInterestIs141Dollars67Cents() {
		assertTrue(LoanCalculator.createAmortizationTable(10000, 950, 0.17).indexOf("141.67") >= 0);
	}

	@Test
	public void testLoanOf10000Percent17Payment950FirstMonthRemainingIs9191Dollars67Cents() {
		assertTrue(LoanCalculator.createAmortizationTable(10000, 950, 0.17).indexOf("9191.67") >= 0);
	}

	@Test
	public void testLoanOf10000Percent17Payment950FifthMonthInterestIs94Dollars88Cents() {
		assertTrue(LoanCalculator.createAmortizationTable(10000, 950, 0.17).indexOf("94.88") >= 0);
	}

	@Test
	public void testLoanOf10000Percent17Payment950LastMonthPaymentIs457Dollars36Cents() {
		assertTrue(LoanCalculator.createAmortizationTable(10000, 950, 0.17).indexOf("457.36") >= 0);
	}

	@Test
	public void testLoanOf13854Percent13Payment278point37TestAllValuesForPerfectFormatting() {
		assertEquals(
				"Month     Interest        Payment         Remaining\n1         $150.09         $278.37         $13725.72\n2         $148.70         $278.37         $13596.05\n3         $147.29         $278.37         $13464.97\n4         $145.87         $278.37         $13332.47\n5         $144.44         $278.37         $13198.54\n6         $142.98         $278.37         $13063.15\n7         $141.52         $278.37         $12926.30\n8         $140.03         $278.37         $12787.96\n9         $138.54         $278.37         $12648.13\n10        $137.02         $278.37         $12506.78\n11        $135.49         $278.37         $12363.90\n12        $133.94         $278.37         $12219.47\n13        $132.38         $278.37         $12073.48\n14        $130.80         $278.37         $11925.91\n15        $129.20         $278.37         $11776.74\n16        $127.58         $278.37         $11625.95\n17        $125.95         $278.37         $11473.53\n18        $124.30         $278.37         $11319.46\n19        $122.63         $278.37         $11163.72\n20        $120.94         $278.37         $11006.29\n21        $119.23         $278.37         $10847.15\n22        $117.51         $278.37         $10686.29\n23        $115.77         $278.37         $10523.69\n24        $114.01         $278.37         $10359.33\n25        $112.23         $278.37         $10193.19\n26        $110.43         $278.37         $10025.25\n27        $108.61         $278.37         $9855.49\n28        $106.77         $278.37         $9683.89\n29        $104.91         $278.37         $9510.43\n30        $103.03         $278.37         $9335.09\n31        $101.13         $278.37         $9157.85\n32        $99.21          $278.37         $8978.69\n33        $97.27          $278.37         $8797.59\n34        $95.31          $278.37         $8614.53\n35        $93.32          $278.37         $8429.48\n36        $91.32          $278.37         $8242.43\n37        $89.29          $278.37         $8053.35\n38        $87.24          $278.37         $7862.22\n39        $85.17          $278.37         $7669.02\n40        $83.08          $278.37         $7473.73\n41        $80.97          $278.37         $7276.33\n42        $78.83          $278.37         $7076.79\n43        $76.67          $278.37         $6875.09\n44        $74.48          $278.37         $6671.20\n45        $72.27          $278.37         $6465.10\n46        $70.04          $278.37         $6256.77\n47        $67.78          $278.37         $6046.18\n48        $65.50          $278.37         $5833.31\n49        $63.19          $278.37         $5618.13\n50        $60.86          $278.37         $5400.62\n51        $58.51          $278.37         $5180.76\n52        $56.12          $278.37         $4958.51\n53        $53.72          $278.37         $4733.86\n54        $51.28          $278.37         $4506.77\n55        $48.82          $278.37         $4277.22\n56        $46.34          $278.37         $4045.19\n57        $43.82          $278.37         $3810.64\n58        $41.28          $278.37         $3573.55\n59        $38.71          $278.37         $3333.89\n60        $36.12          $278.37         $3091.64\n61        $33.49          $278.37         $2846.76\n62        $30.84          $278.37         $2599.23\n63        $28.16          $278.37         $2349.02\n64        $25.45          $278.37         $2096.10\n65        $22.71          $278.37         $1840.44\n66        $19.94          $278.37         $1582.01\n67        $17.14          $278.37         $1320.78\n68        $14.31          $278.37         $1056.72\n69        $11.45          $278.37         $789.80\n70        $8.56           $278.37         $519.99\n71        $5.63           $278.37         $247.25\n72        $2.68           $249.93         $0.00\n",
				LoanCalculator.createAmortizationTable(13854, 278.37, 0.13));
	}

}
