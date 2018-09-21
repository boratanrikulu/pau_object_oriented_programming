/*
	NOT: Bu program IDE kullanilmadan yazilmistir.
	Testleri elle derlenerek, java-9-openjdk ile yapilmistir.

	Lütfen proje icindeki README.md dosyasini okuyunuz..
*/
public class Dikdortgen {
	private static int x; // noktanin x koordinati
	private static int y; // noktanin y koordinati
	private static int height; // yukseklik
	private static int width; // genislik

	public Dikdortgen(int x, int y, int height, int width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	public void shift(int x, int y) {
		this.x = this.x + x;
		this.y = this.y + y;
	}

	public void setHeightWidth(int height, int width) {
		this.height = height;
		this.width = width;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getAlan() {
		return (this.width * this.height);
	}

	public int getCevre() {
		return (2*this.width + 2*this.height);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	/* set islemini kolaylastirmak icin bir parametre */
	public void setter(int x, int y, int height, int width) {
		setXY(x, y);
		setHeightWidth(height, width);
	}

	/********************/
	/*		Display		*/
	public void getter() {
		// getters
		System.out.print("["+getX()+","+getY()+"]");
		System.out.print(" koordinatlarında bulunan;");
		System.out.print(" alani \""+getAlan()+"\",");
		System.out.print(" cevresi \""+getCevre()+"\" olan dikdortgen.\n");
	}
	public void shifter(int x, int y) {
		System.out.println("Dikdortgen ["+x+","+y+"] birim otelenirse;");
		shift(x,y);
	}
	/********************/
}