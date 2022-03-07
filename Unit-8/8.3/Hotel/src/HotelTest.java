import static org.junit.Assert.*;

import org.junit.*;

public class HotelTest {

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


}
