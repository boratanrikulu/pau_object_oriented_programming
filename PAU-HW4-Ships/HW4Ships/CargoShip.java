/*
	NOTE: This program is written without using any IDE.
	Tests were completed manually by using java-9-openjdk.
	Please read the README.md file that is in the project.
*/
package Ships;

public class CargoShip extends Ship {

	private int deadweightTonnage;

	// constructor
	public CargoShip(String shipName, String builtYear, int deadweightTonnage) {
		super(shipName, builtYear);

		if(deadweightTonnage < 1) // Throws an error when the input is lower than 1.
			throw new IllegalArgumentException("(!) Do not enter negative values as Deadweight Tonnage.");

		this.deadweightTonnage = deadweightTonnage;
	}

	// setter
	public void setDeadweightTonnage(int deadweightTonnage) {
		if(deadweightTonnage < 0)
			throw new IllegalArgumentException("(!) Do not enter negative values as Deadweight Tonnage.");
		
		this.deadweightTonnage = deadweightTonnage;
	}

	// getter
	public int getDeadweightTonnage() {
		return deadweightTonnage;
	}

	// toString
	@Override
	public String toString() {
		return super.toString() + String.format(",\t [Deadweight Tonnage: %d]", this.deadweightTonnage);
	}

}