/*
	NOT: Bu program IDE kullanilmadan yazilmistir.
	Testleri elle derlenerek, java-9-openjdk ile yapilmistir.

	Lütfen proje icindeki README.md dosyasini okuyunuz..
*/
import java.util.Scanner;

public class DikdortgenTest {

	public static void main(String[] args) {
		System.out.println();
		System.out.println("# Dikdortgen_1 #");
		Dikdortgen dikdortgen1 = new Dikdortgen(0, 0, 1, 11);
		dikdortgen1.getter(); // nesnenin degerleri gosterilir
		System.out.println();
		
		System.out.println("# Dikdortgen_2 #");
		Dikdortgen dikdortgen2 = new Dikdortgen(9, 2, 80, 38);
		dikdortgen2.getter(); // nesnenin degerleri gosterilir
		dikdortgen2.shifter(5,-3); // 5 birim yukari, 3 birim sola kaydirma islemi yapilir
		dikdortgen2.getter(); // yeni sonuc gozlemlenmek icin nesnenin degerleri tekrar gosterilir
		System.out.println();

		System.out.println("# Dikdortgen_3 #");
		Dikdortgen dikdortgen3 = new Dikdortgen(3, 1, 877, 21);
		dikdortgen3.getter(); // nesnenin degerleri gosterilir
		dikdortgen3.shifter(-41,41); // 41 birim asagi, 41 saga kaydirma islemi yapilir
		dikdortgen3.getter(); // yeni sonuc gozlemlenmek icin nesnenin degerleri tekrar gosterilir
		// Dikdortgen 3 icin setter parametresi ile degerlerin degistirilmesi
		System.out.println("Dikdortgen 3'un degerleri (5, 2, 23, 65) olarak guncellenirse;");
		dikdortgen3.setter(5, 2, 23, 65);
		dikdortgen3.getter();
		System.out.println();
	}
}