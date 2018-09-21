/*
	NOTE: This program is written without using any IDE.
	Tests were completed manually by using java-9-openjdk.
	Please read the README.md file that is in the project.
*/
package Ships;

public class ShipTest {

	public static void main(String[] args) {
		
		Ship[] ships = new Ship[3];

		ships[0] = new Ship("Nusret Minelayer", "4 December 1911");
		ships[1] = new TouristShip("RMS Titanic", "15 April 1912", 2435);
		ships[2] = new CargoShip("MSC Flaminia", "26 May 2001", 75590);

		for(Ship temp : ships) { // copies all the objects to the temp and call the toString method.
			System.out.println(temp.toString());
		}
	}
}