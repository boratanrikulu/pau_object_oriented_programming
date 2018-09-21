/*
	NOTE: This program is written without using any IDE.
	Tests were completed manually by using java-9-openjdk.
	Please read the README.md file that is in the project.
*/
import java.util.Scanner;

public class RaceTest {

	public static void main(String[] args) {
		// objects
		Scanner scan = new Scanner(System.in);
		Race race = new Race();

		// questions
		System.out.print("How many tortoises? ");
		int tortoiseNumber = scan.nextInt();
		System.out.print("How many hares? ");
		int haresNumber	= scan.nextInt();

		// control and settings
		if(tortoiseNumber < 0 || haresNumber < 0)
			System.out.println("(!) Do not use negative values!");

		else if(tortoiseNumber == 0 && haresNumber == 0)
			System.out.println("(!) There are no runners!");

		else {
			race.setTortoises(tortoiseNumber); // sends the number of tortoise to the race object.
			race.setHares(haresNumber); // sends the number of hares to the race object.

			race.startRace(); // starts the race!.
		}
	}
}