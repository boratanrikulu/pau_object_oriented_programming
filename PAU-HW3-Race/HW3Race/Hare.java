/*
	NOTE: This program is written without using any IDE.
	Tests were completed manually by using java-9-openjdk.
	Please read the README.md file that is in the project.
*/
import java.security.SecureRandom;

public class Hare {
	private int move;
	private int chance;

	// setters
	public void setChance() {
		SecureRandom srandom = new SecureRandom();
		this.chance = srandom.nextInt(10) + 1; // generates a random number
	}
	public void setMove() { // changes the "move" with the move type information.
		if(this.chance>0 && this.chance<=2)
			this.move += 0;
		else if(this.chance>2 && this.chance<=4)
			this.move += 9;
		else if(this.chance>4 && this.chance<=5)
			this.move += -12;
		else if(this.chance>5 && this.chance<=8)
			this.move += 1;
		else if(this.chance>8 && this.chance<=10)
			this.move += -2;
	}

	// getter
	public int getMove() {
		if(this.move < 0) // shows 0 if it is less than zero.
			this.move = 0;

		if(this.move >=69) // shows 69 if it is greater than 69.
			this.move = 69;
		
		return this.move;
	}
}