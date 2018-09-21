/*
	NOT: Bu program IDE kullanilmadan yazilmistir.
	Testleri elle derlenerek, java-9-openjdk ile yapilmistir.

	Lütfen proje icindeki README.md dosyasini okuyunuz..
*/
import java.util.Scanner;

public class Vigenere {

	public static void main(String[] args) {
		String plaintext; // sifrelenecek metin
		String ciphertext; // sifrelenmis metin
		String key; // anahtar

		// Objects
		Scanner scan = new Scanner(System.in);
		Encryption encrypt = new Encryption();
		Decryption decrypt = new Decryption();

		while(true){
			// Menu
			System.out.println("###############################");
			System.out.println("#       VIGENERE CIPHER       #");
			System.out.println("#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#");
			System.out.println("# 1) Encryption (sifreleme)   #");
			System.out.println("# 2) Decryption (sifre çözme) #");
			System.out.println("# 9) Cikis                    #");
			System.out.println("###############################");
			System.out.print("\t\t Secenek: "); int menu = scan.nextInt();
			
			// Encrypt and Decrypt
			switch(menu) {
				// Encryption (Şifreleme)
				case 1:
					// Sifrelenecek metninin kullanicidan alinmasi
					System.out.print("Sifrelenecek Metin: ");
					scan.nextLine(); // Fazladan bir "enter" aldigi icin bu sekilde absorbe ettim

					plaintext = scan.nextLine();
					encrypt.setPlainText(plaintext.toLowerCase());

					// Sifreleme işlemi için anahtarin kullanicidan alinmasi
					System.out.print("Sifreleme Icin Anahtar: ");
					key = scan.nextLine();

					// Sifreleme işlemi
					encrypt.encrypt(key.toLowerCase());

					// Sifrelenmis verininin ekrana basilmasi
					System.out.println("Sifrelenmis Metin: "+ encrypt.getCipherText());
					break;
				
				// Decryption (Sifre Cozme)
				case 2:
					// Şifrelenen metninin kullanicidan alinmasi
					System.out.print("Sifrelenmis Metin: "); 
					scan.nextLine(); // Fazladan bir "enter" aldigi icin bu sekilde absorbe ettim

					ciphertext = scan.nextLine();
					decrypt.setCipherText(ciphertext.toLowerCase());

					// Sifre açma işlemi için anahtarin kullanicidan alinmasi
					System.out.print("Sifre Acma Icin Anahtar: ");
					key = scan.nextLine();

					// Sifre açma işlemi
					decrypt.decrypt(key.toLowerCase());

					// Sifresi cozulmus verinin ekrana basilmasi
					System.out.println("Sifresi Cozulmus Metin: "+ decrypt.getPlainText());
					break;
				case 9:
					System.exit(0);
					break;
				default:
					System.out.println("(!) Menu Seceneklerine Uygun Girdi Yapiniz");
					break;
			}
			System.out.println(); System.out.println();
		}
	}
}