/*
	NOT: Bu program IDE kullanilmadan yazilmistir.
	Testleri elle derlenerek, java-9-openjdk ile yapilmistir.
*/

import java.util.Scanner;

public class EandFakt{

	public double faktoriyel(int value){
		double result = 1;	
		for(double i=value; i>0; i--){
			result *= i;
		}
		return result;
	}

	public double eValue(int value){
		EandFakt object = new EandFakt();
		double result=1;
		for (int i=1; i<=value; i++){
			result += (double)1/object.faktoriyel(i);
		}
		return result;
	}

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		EandFakt object = new EandFakt();
		while(true){
			System.out.println("----------------------------");
			System.out.println("| 1) Faktoriyel             |");
			System.out.println("| 2) E Sayisi               |");
			System.out.println("| 9) Cikis                  |");
			System.out.println("----------------------------");
			System.out.print("\t\t Secenek: "); int menu = input.nextInt();

			int value;
			switch(menu){
				case 1: 
					System.out.print("Faktoriyeli hasaplanacak sayiyi: ");
					if((value = input.nextInt())<0)
						System.out.println("(!) Negatif Girdi Yapmayiniz");
					else
						System.out.println(value+"! = "+object.faktoriyel(value));
					
					break;

				case 2:
					System.out.print("E Sayisini hesaplamak icin hassasiyet degeri: ");
					if((value = input.nextInt())<=0)
						System.out.println("(!) Pozitif Girdi Yapiniz");
					else
						System.out.println(value+" hassasiyeti ile E Sayisi = "+object.eValue(value));

					break;

				case 9:
					System.exit(0);
					break;

				default:
					System.out.println("(!) Menu Seceneklerine Uygun Girdi Yapiniz");
					break;
			}

			System.out.println("\n\n\n");
		}
	}

}