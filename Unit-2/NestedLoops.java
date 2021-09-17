
public class NestedLoops {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		nestedPrint();
	}

	private static void nestedPrint() {
		int num1 = 0;
		int num2 = 0;


		while (num1 < 10) {
			while (num2 < 10) {
				num2++;
				System.out.println(num1 + " " + num2);
			}
			System.out.print(num1 + " ");
			num1++;

		}

	}

}
