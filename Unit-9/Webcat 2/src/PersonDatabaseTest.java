import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class PersonDatabaseTest {
	
	private static Person[] personArray;
	private static int size = 40000;
	
	@BeforeClass
	public static void setup() {
		personArray = new Person[size];
		personArray[0] = new Person("John", "Smith", 29, 3, 1980);
		personArray[1] = new Person("John", "Smith", 30, 4, 2001);
		personArray[2] = new Person("Jin", "Xiolang", 30, 11, 1571);
		personArray[3] = new Person("Banni","Singh", 30, 11, 1571);
		personArray[4] = new Person("Jin", "Xiolang", 30, 4, 2001);
		personArray[5] = new Person("Azalea", "Malloy", 30, 4, 2001);
		personArray[6] = new Person("Rene", "Salvry", 30, 4, 2001);
		personArray[7] = new Person("Jane", "Brimmer", 30, 4, 2001);
		personArray[8] = new Person("John", "Smith", 30, 4, 2000);
		personArray[9] = new Person("John", "Smith", 30, 3, 2001);
		personArray[10] = new Person("John", "Smith", 29, 4, 2001);

		for (int i = 11; i < size; i++) {
			int birthYear = 1500 + ((i / 28) / 12);
			int birthMonth = 1 + (i / 28) % 12;
			int birthDay = 1 + i % 28;
			personArray[i] = new Person("F" + (i % 1000), "L" + i, birthDay, birthMonth, birthYear);
			
		}
		
		//shuffle so that binary search tree is fast
		for (int i = 11; i < size; i++) {
			int index = (int)(Math.random() * (size - 11)) + 11;
			Person temp = personArray[i];
			personArray[i] = personArray[index];
			personArray[index] = temp;
		}
	}
	
	@Test(timeout = 6000)
	public void testPutNoDuplicatesAllowed() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < 12; i++) {
			db.put(personArray[i]);
		}
		
		assertFalse(db.put(personArray[4]));
		assertFalse(db.put(personArray[3]));
		assertFalse(db.put(personArray[7]));
		if (db.size() < 12) {
			fail("The database didn't keep update size properly");
		}
		if (db.size() > 12) {
			fail("The database allowed a duplicate person to be entered");
		}
	}
	
	@Test()
	public void testPutFirst11NameNodesCorrect() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < 11; i++) {
			db.put(personArray[i]);
		}

		if (db.size() != 11) {
			assertEquals(11, db.size());
			fail("The size of the database is incorrect."
					+ "Either size wasn't updated correctly"
					+ " or all of the people didn't get entered.");
		}
		
		Node root = db.getNameRoot();
		// Check root properly set
		assertEquals("Smith", root.item.lastName);
		assertEquals(1980, root.item.birthYear);
		
		// Check right side of tree
		Node jin = root.right.left; 
		assertEquals("Xiolang", jin.item.lastName);
		assertEquals(2001, jin.item.birthYear);
		
		// Check left side of tree
		Node rene = root.left.left.left.right;
		assertEquals("Salvry", rene.item.lastName);

		Node john = root.left.left.right;
		assertEquals("Smith", john.item.lastName);
		assertEquals(2000, john.item.birthYear);
		
	}
	
	@Test(timeout = 60000)
	public void testPutFirst11BirthdayNodesCorrect() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < 11; i++) {
			db.put(personArray[i]);
		}
		
		if (db.size() != 11) {
			fail("The size of the database is incorrect."
					+ "Either size wasn't updated correctly"
					+ " or all of the people didn't get entered.");
		}
		
		Node root = db.getBDayRoot();
		// Check root properly set
		assertEquals(1980, root.item.birthYear);
		assertEquals("Smith", root.item.lastName);
		
		// Check right side of tree
		Node jane = root.right.left.left.left.left; 
		assertEquals(2001, jane.item.birthYear);
		assertEquals("Brimmer", jane.item.lastName);
		
		// Check left side of tree
		Node john = root.right.left.left.left.left.left.right.right;
		assertEquals(2001, john.item.birthYear);
		assertEquals(29, john.item.birthDay);
		assertEquals("Smith", john.item.lastName);
	}
	
	@Test(timeout = 6000)
	public void testPutTimeAndSizeCorrect() {
		long startTime = System.currentTimeMillis();
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < size; i++) {
			db.put(personArray[i]);
		}
		long endTime = System.currentTimeMillis();
		double time = (endTime - startTime) / 1000.0;
		System.out.println("Put time: " + time);
		if (time > 1) {
			fail("Took " + time + " seconds to put everyone in the database." 
					+ " It needs to take less than 1 second");
		}		
		if (db.size() != size) {
			fail("The size of the database is incorrect."
					+ "Either size wasn't updated correctly"
					+ " or all of the people didn't get entered.");
		}
	}
	
	@Test(timeout = 60000)
	public void testFindWithNameRetrievesAllJohnSmiths() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < size; i++) {
			db.put(personArray[i]);
		}
	
		List<Person> list = db.find("John", "Smith");
		Integer[] indicesOfJohnSmith = {0, 1, 8, 9, 10};
		for (Integer index : indicesOfJohnSmith) {
			boolean contains = false;
			for (Person p : list) {
				if (p == personArray[index]) {
					contains = true;
					break;
				}
			}
			if ( ! contains) {
				fail("Find by name didn't find all of the people" 
						+ " with the name John Smith");
			}
		}
		
		
		list = db.find("Marilyn", "Monroe");
		if ( list == null || ! list.isEmpty()) {
			fail("Trying to find a person by name who isn't in "
					+ "the database failed to return an empty list.");
		}
		
		boolean canFindAllByName = true;
		for (int i = 11; i < size; i++) {
			Person p = personArray[i];
			list = db.find(p.firstName, p.lastName);
			if (list.size() == 1) {
				if (list.get(0) != p) {
					canFindAllByName = false;
					break;
				}
			}
		}
		if (! canFindAllByName) {
			fail("Wasn't able to find every person in the database "
					+ "by using the find by name method.");
		}
	}

	@Test(timeout = 6000)
	public void testFindWithNameSpeed() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < size; i++) {
			db.put(personArray[i]);
		}
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			Person p = personArray[i];
			db.find(p.firstName, p.lastName);
		}
		long endTime = System.currentTimeMillis();
		double time = (endTime - startTime) / 1000.0;
		System.out.println("Find by name time: " + time);
		if (time > 1) {
			fail("Took " + time + " seconds to find everyone by name." 
					+ " It needs to take less than 1 second");
		}		
	}
	
	@Test(timeout = 6000)
	public void testFindWithNameCanFindEveryPerson() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < size; i++) {
			db.put(personArray[i]);
		}
		
		List<Person> list = db.find("Marilyn", "Monroe");
		if ( list == null || ! list.isEmpty()) {
			fail("Trying to find a person by name who isn't in "
					+ "the database failed to return an empty list.");
		}
		
		boolean canFindAllByName = true;
		for (int i = 11; i < size; i++) {
			Person p = personArray[i];
			list = db.find(p.firstName, p.lastName);
			if (list.size() == 1) {
				if (list.get(0) != p) {
					canFindAllByName = false;
					break;
				}
			}
		}
		if (! canFindAllByName) {
			fail("Wasn't able to find every person in the database "
					+ "by using the find by name method.");
		}	
	}
	
	@Test(timeout = 6000)
	public void testFindWithDateRetrievesAll2001_4_30() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < size; i++) {
			db.put(personArray[i]);
		}
		
		List<Person> list = db.find(30, 4, 2001);
		Integer[] indicesOf30042001 = {1, 4, 5, 6, 7};
		for (Integer index : indicesOf30042001) {
			boolean contains = false;
			for (Person p : list) {
				if (p == personArray[index]) {
					contains = true;
					break;
				}
			}
			if ( ! contains) {
				fail("Find by date didn't find all of the people" 
						+ " with the birthday 30/4/2001");
			}
		}
	}
	
	@Test(timeout = 6000)
	public void testFindWithDateSpeed() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < size; i++) {
			db.put(personArray[i]);
		}
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			Person p = personArray[i];
			db.find(p.birthDay, p.birthMonth, p.birthYear);
		}
		long endTime = System.currentTimeMillis();
		double time = (endTime - startTime) / 1000.0;
		System.out.println("Find by date time: " + time);
		if (time > 1) {
			fail("Took " + time + " seconds to find everyone by birthdate." 
					+ " It needs to take less than 1 second");
		}
	}
	
	@Test(timeout = 6000)
	public void testFindWithDateCanFindEveryPerson() {
		PersonDatabase db = new PersonDatabase();
		for (int i = 0; i < size; i++) {
			db.put(personArray[i]);
		}
	
		List<Person> list = db.find(5, 5, 1400);
		if ( list == null || ! list.isEmpty()) {
			fail("Trying to find a person by birthdate who isn't in "
					+ "the database failed to return an empty list.");
		}
		
		boolean canFindAllByDate = true;
		for (int i = 11; i < size; i++) {
			Person p = personArray[i];
			list = db.find(p.birthDay, p.birthMonth, p.birthYear);
			if (list.size() == 1) {
				if (list.get(0) != p) {
					canFindAllByDate = false;
					break;
				}
			}
		}
		if (! canFindAllByDate) {
			fail("Wasn't able to find every person in the database "
					+ "by using the find by birthdate method.");
		}	
	}
}
