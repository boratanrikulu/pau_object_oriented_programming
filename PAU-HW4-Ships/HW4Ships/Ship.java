/*
	NOTE: This program is written without using any IDE.
	Tests were completed manually by using java-9-openjdk.
	Please read the README.md file that is in the project.
*/
package Ships;

public class Ship {

	private String shipName;
	private String builtYear;

	// constructor
	public Ship(String shipName, String builtYear) {
		if(shipName == "") // Throws an error when the input is null.
			throw new IllegalArgumentException("(!) Ship Name not entered.");
		if(builtYear == "")
			throw new IllegalArgumentException("(!) Year of Built not entered.");
		
		this.shipName = shipName;
		this.builtYear = builtYear;
	}

	// setters
	public void setShipName(String shipName) {
		if(shipName == "")
			throw new IllegalArgumentException("(!) Ship Name not entered.");
		
		this.shipName = shipName;
	}
	public void setBuiltYear(String builtYear) {
		if(builtYear == "")
			throw new IllegalArgumentException("(!) Year of Built not entered.");
		
		this.builtYear = builtYear;
	}

	// getters
	public String getShipName() {
		return this.shipName;
	}
	public String getBuiltYear() {
		return this.builtYear;
	}

	// toString
	public String toString() {
		return String.format("Ship: %s,\t[Date: %s]", this.shipName, this.builtYear);
	}

}