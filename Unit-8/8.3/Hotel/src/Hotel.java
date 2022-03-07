import java.util.ArrayList;

public class Hotel {
	
    public Hotel(int numberOfRooms) {
    		rooms = new Reservation[numberOfRooms];
    		waitList = new ArrayList<String>();
    }

	// each element of rooms corresponds to a room in the hotel;
	// if rooms[index] is null, the room is empty;
	// otherwise, it contains a reference to the Reservation 
	// for that room, such that
	// rooms[index].getRoomNumber() returns index
	// Whenever a Reservation object is created, it should be
	// added to this array in the correct position
	private Reservation[] rooms;
	
	// waitList contains names of guests who have not yet been
	// assigned a room because all rooms are full
	private ArrayList<String> waitList;
	
	// if there are any empty rooms (rooms with no reservation), 
	// 		then create a reservation for an empty room for the
	// 		specified guest and return the new Reservation;
	// otherwise, 
	//	add the guest to the end of waitList
	// 	and return null
	public Reservation requestRoom(String guestName) {
		  for (int i = 0; i < rooms.length; i++) {
		    if (rooms[i] == null) {
		      rooms[i] = new Reservation(guestName, i);
		      return rooms[i];
		    }
		  }
		  waitList.add(guestName);
		  return null;
	}
	
	// release the room associated with parameter res, effectively
	// canceling the reservation;
	// if any names are stored in waitList, 
	//		remove the first name
	// 		and create a Reservation for this person in the room
	// 		reserved by res; 
	//		return that new Reservation;
	// if waitList is empty, 
	//		mark the room specified by res as empty
	// 		and return null
	// precondition: res is a valid Reservation for some room in this hotel
	public Reservation cancelAndReassign(Reservation res) {
		  int roomNumber = res.getRoomNumber(); // cancelled person’s room num
		  if (waitList.size() > 0) {
		    String name = (String)waitList.get(0); // b/c blank ArrayList is ArrayList<Object>…typecast is optional
		    waitList.remove(0);
		    rooms[roomNumber] = new Reservation(name, roomNumber); // re-assign cancelled room with old roomNum + new name
		  }
		  else {
		    rooms[roomNumber] = null;
		  }
		  return rooms[roomNumber];
	}
	
	
	
	// constructors and other methods not shown
	
	//****these methods were added to create a public interface that 
	// is allows test to be written.  For example, without setReservation(),
	// to perform our JUnit setUp(), we would have to go in and modify the 
	// private Reservation[] rooms from the outside, in the HotelTest.java class.  
	// This violates the Object-Oriented principle of encapsulation and 
	// hiding implementation details to the 'outside'
	public void setReservation(String name, int roomNum) {
		rooms[roomNum] = new Reservation(name, roomNum);
	}
	
	public Reservation getReservation(int roomNum) {
		return rooms[roomNum];
	}
	
	public String getWaitListName(int spotOnWaitlist) {
		return (String)waitList.get(spotOnWaitlist);
	}
 }

    
  
