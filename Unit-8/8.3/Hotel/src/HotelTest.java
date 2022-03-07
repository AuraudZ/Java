import static org.junit.Assert.*;

import org.junit.*;

public class HotelTest {

	 Hotel testHotel;
	@Before  // Anything marked with the @Before annotation is run before each test
	public void setUp() throws Exception {
		testHotel = new Hotel(10);
		// manually set a default 'state' for this Hotel of 10 rooms
		// note that normally we don't want to allow Reservations to be created outside of Hotel
		// also note the syntax for creating a new instance of an 'inner' class
		
		testHotel.setReservation("Aaron", 0);
		testHotel.setReservation("Beth", 1);
		testHotel.setReservation("Carlos", 2);
		testHotel.setReservation("Dawn", 3);
		testHotel.setReservation("Ethan", 4);
		
		testHotel.setReservation("Faye", 5);
		testHotel.setReservation("Gary", 6);
		testHotel.setReservation("Haley", 7);
		// room 8 is empty (null)
		testHotel.setReservation("Ingrid", 9);
	}

	@Test
	public void testRequestRoomReservationSaved() {
		// test that a reservation request is successful
		testHotel.requestRoom("New Guy"); // Room 8 should have a reservation object
		assertEquals("New Guy", testHotel.getReservation(8).getGuestName());
		assertEquals(8, testHotel.getReservation(8).getRoomNumber());
	}

	@Test
	public void testRequestRoomReservationReturnedProperly() {
		Reservation r =  testHotel.requestRoom("New Guy");
		assertEquals(r, testHotel.getReservation(8));
	}

	@Test
	public void testRequestRoomHotelFullShouldWaitList() {
		testHotel.requestRoom("New Guy"); // Hotel is full
		testHotel.requestRoom("Unlucky Person"); // Should be on waitlist
		testHotel.requestRoom("Unlucky Person 2"); // Should be on waitlist
		assertEquals("Unlucky Person", testHotel.getWaitListName(0));
		assertEquals("Unlucky Person 2", testHotel.getWaitListName(1));
	}

	@Test
	public void testRequestRoomHotelFullShouldReturnNull() {
		testHotel.requestRoom("New Guy"); // Hotel is full
		Reservation r =	 testHotel.requestRoom("Unlucky Person"); // Should be on waitlist
		assertNull(r);
	}
}
