/*
	NOTE: This program is written without using any IDE.
	Tests were completed manually by using java-9-openjdk.
	Please read the README.md file that is in the project.
*/
package Ships;

public class TouristShip extends Ship {

	private int passengerCapacity;

	// constructor
	public TouristShip(String shipName, String builtYear, int passengerCapacity) {
		super(shipName, builtYear);

		if(passengerCapacity < 1) // Throws an error when the input is lower than 1.
			throw new IllegalArgumentException("(!) Do not enter negative values as Passenger Capacity.");

		this.passengerCapacity = passengerCapacity;
	}

	// setter
	public void setPassengerCapacity(int passengerCapacity) {
		if(passengerCapacity < 0)
			throw new IllegalArgumentException("(!) Do not enter negative values as Passenger Capacity.");
		
		this.passengerCapacity = passengerCapacity;
	}

	// getter
	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	// toString
	@Override
	public String toString() {
		return super.toString() + String.format(",\t [Passenger Capacity: %d]", this.passengerCapacity);
	}

}