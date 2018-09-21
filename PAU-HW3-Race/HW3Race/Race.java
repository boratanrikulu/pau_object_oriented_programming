/*
	NOTE: This program is written without using any IDE.
	Tests were completed manually by using java-9-openjdk.
	Please read the README.md file that is in the project.
*/
import java.util.ArrayList;

public class Race {
	private final int RACE_LENGTH = 70;
	private int tortoisesNumber;
	private int haresNumber;
	private Hare[] hare;
	private Tortoise[] tortoise;
	private int winnersNumber;
	private int counter;

	// methods
	public void startRace() {
		// arrays
		hare = new Hare[haresNumber];
		tortoise = new Tortoise[tortoisesNumber];
		ArrayList<Integer> tortoiseWinners = new ArrayList<Integer>(); // builds an arraylist for the winner tortoises.
		ArrayList<Integer> hareWinners = new ArrayList<Integer>(); // builds an arraylist for the winner hares.

		// objects
		for (int i=0; i < hare.length; i++) // builds the hare objects that defined as an array.
			hare[i] = new Hare();
		for(int i=0; i < tortoise.length; i++) // builds the tortoise objects that defined as an array.
			tortoise[i] = new Tortoise();

		while(winnersNumber<=0) {
			System.out.printf("Round %d\n", (++counter));

			// race and display loops
			for(int i=0; i < tortoise.length; i++) {
				tortoise[i].setChance();
				tortoise[i].setMove();
				display(tortoise[i].getMove(), 'T'); // shows the current tortoise runner position with "T". 
			}
			for(int i=0; i < hare.length; i++) {
				hare[i].setChance();
				hare[i].setMove();
				display(hare[i].getMove(), 'H'); // shows the current hare runner position with "H".
			} System.out.println("\n");

			// control loops for the winner
			for (int i=0; i < tortoise.length; i++) {
				if(isItWinner(tortoise[i].getMove())) {
					tortoiseWinners.add(i); // sends the current tortoise to the arraylist if the is a winner.
				}
			}
			for (int i=0; i < hare.length; i++) {
				if(isItWinner(hare[i].getMove())) {
					hareWinners.add(i); // sends the current hare to the arraylist if it is a winner.
				}
			}
		}

		showWinners(tortoiseWinners, hareWinners); // shows all winners.
	}

	public boolean isItWinner(int position){
		if(position == (RACE_LENGTH-1)) { // checks winning situations.
			this.winnersNumber++;
			return true;
		}
		return false;
	}

	public void showWinners(ArrayList<Integer> tortoiseWinners, ArrayList<Integer> hareWinners) {
		if(winnersNumber > 1) { // If there are more than one winner.
			System.out.print("It's a tie between");
			for (int i=0; i<tortoiseWinners.size(); i++) {
				System.out.print(" Tortoise[" + ((tortoiseWinners.get(i))+1) + (i==(tortoiseWinners.size()-1) ? ("] at Round "+(counter)) : "],"));
			}
			for (int i=0; i<hareWinners.size(); i++) {
				System.out.print(" Hare[" + ((hareWinners.get(i))+1) + (i==(hareWinners.size()-1) ? ("] at Round "+(counter)) : "],"));
			}
			System.out.println();
		}		

		else { // If there is only one winner.
			for (int i=0; i<tortoiseWinners.size(); i++) {
				System.out.println("Tortoise[" + ((tortoiseWinners.get(i))+1) + "] won" + (" at Round "+(counter)) + "! YAY!!");
			}
			for (int i=0; i<hareWinners.size(); i++) {
				System.out.println("Hare[" + ((hareWinners.get(i))+1) + "] won" + (" at Round "+(counter)) + "! YAY!!");
			}
		}
	}

	public void display(int location, char c) {
		for(int i=0; i<RACE_LENGTH; i++) {
			if(location == i)
				System.out.printf("%s", c); // shows "H" or "T" if the "i" is current position of runner.
			else
				System.out.printf("_"); // shows "_" if the "i" is not current position of runner.
		}
		System.out.println();
	}

	// setters
	public void setTortoises(int tortoisesNumber) {
		this.tortoisesNumber = tortoisesNumber;
	}
	public void setHares(int haresNumber) {
		this.haresNumber = haresNumber;
	}
}