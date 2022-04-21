
import java.util.*;

public class Gradebook {

	private List<Assignment> assignments = new ArrayList<Assignment>();

	
	public Gradebook() {
		assignments.add(new Homework("HW 1", "2016, 1, 7", 4, 4, false));
		assignments.add(new Homework("HW 2", "2016, 1, 8", 3, 4, false));
		assignments.add(new Homework("HW 3", "2016, 1, 9", 0, 4, false));
		assignments.add(new Test("Ch 5 Test", "2016, 1, 15", 12, 15));
		assignments.add(new Homework("HW 4", "2016, 1, 15", 2, 4, false));
		assignments.add(new Homework("HW 5", "2016, 1, 22", 3, 4, false));
		assignments.add(new Test("Ch 6 Test", "2016, 1, 28", 21, 30));
		assignments.add(new Homework("HW 6", "2016, 1, 26", 3, 4, false));
		assignments.add(new Homework("HW 7", "2016, 1, 28", 4, 4, false));
		assignments.add(new Test("Ch 7 Test", "2016, 2, 4", 8, 14));
		assignments.add(new FinalExam("Semester 1 Final", "2016, 2, 6", 78, 100));
	}
	
	// Horribly hardcoded!  Can we redesign to be cleaner?
	public void printOutAssignments() {
		for (Assignment assignment : assignments) {
			System.out.println(assignment.getName() + " (" +
					assignment.getDateAssigned() + ")  --" +
					assignment.getPointsPossible() + "/" +
					assignment.getPointsEarned() + "--");
		}
	}
}
