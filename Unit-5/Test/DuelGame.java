// Potential Giant names if you are feeling uncreative:
// Goliath, Cyclops, Titan, Paul Bunyan, Andre, The BFG, Grog, Thag
// Potential Elf names if you are feeling uncreative:
// Galadriel, Legolas, Elrond, Tauriel, Dobby, Link, Snap, Crackle, Pop
// Potential Wizard names if you are feeling uncreative:
// Harry, Ron, Dumbledore, Voldemort, Gandalf, Saruman, Tim the Enchanter, Merlin

public class DuelGame {

	public static void main(String[] args) {
		// 1) Create an array that holds 2 Giants, 2 Elfs, 2 Wizards.
		FantasyCharacter[] characters = new FantasyCharacter[6];
		// 2) Randomly select 2 DIFFERENT participants to duel, and then start the duel.
		characters[0] = new Giant("Massive");
		characters[1] = new Elf("Link");
		characters[2] = new Wizard("Harry");
		characters[3] = new Giant("BFG");
		characters[4] = new Elf("Elrond");
		characters[5] = new Wizard("Ron");

		// THIS PART IS ONLY WORTH .5 POINTS, ~2% OF CODING TEST GRADE
		// 3) Ensure that two members from the same race CAN'T duel each other
		// (e.g Giant vs. Giant, Elf vs. Elf, Wizard vs. Wizard is not OK)
		// How to do this??
		// I wrote my own subroutine for this not sure if that is ok.-AZ
		// From the text: "It is even possible to test whether a given object
		// belongs to a given class, using the 'instanceof' operator. Example:
		// Vehicle myVehicle = new Car();
		// if (myVehicle instanceof Car) {...} --> would evaluate to true, evaluate {}
		// if (myVehicle instanceof Truck) {...} --> would evaluate to false, skip {}
		// change this to select 2 different participants, from 2 different races from a group of 6
		int index = (int) (Math.random() * 5);
		FantasyCharacter c1 = characters[index];
		index = (int) (Math.random() * 5);
		FantasyCharacter c2 = characters[index];
		if (checkType(c1, c2) == 0) {
			return;
		}
		Duel duel = new Duel(c1, c2);
		duel.startDuel();
	}

	public static int checkType(FantasyCharacter c1, FantasyCharacter c2) {
		if (c1 instanceof Wizard && c2 instanceof Wizard) {
			System.out.println("Wizards cannot duel each other");
			return 0;
		}
		if (c1 instanceof Elf && c2 instanceof Elf) {
			System.out.println("Elves cannot duel each other");
			return 0;
		}
		if (c1 instanceof Giant && c2 instanceof Giant) {
			System.out.println("Giants cannot duel each other");
			return 0;
		}
		return 1;
	}
}
