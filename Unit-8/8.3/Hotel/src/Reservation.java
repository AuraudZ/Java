public class Reservation {
	private String guestName;
	private int roomNumber;

	public Reservation(String guestName, int roomNumber) {
		this.guestName = guestName;
		this.roomNumber = roomNumber;
	}
	
	public String getGuestName() {
		return guestName;
	}

	public int getRoomNumber(){ 
		return roomNumber; 
	}
	// private data and other methods not shown
}