/*
	NOT: Bu program IDE kullanilmadan yazilmistir.
	Testleri elle derlenerek, java-9-openjdk ile yapilmistir.

	Lütfen proje icindeki README.md dosyasini okuyunuz..
*/
public class Encryption{
	public final String alphabet = "abcçdefgğhıijklmnoöprsştuüvyz";
	public static String plaintext; // sifrelenecek metin
	public static StringBuilder ciphertext = new StringBuilder();

	public void encrypt(String key){
		int rankofplaintext;
		int rankofkey;

		ciphertext = new StringBuilder(""); // ciphertext'in icini temizler
		plaintext = plaintext.replace(" ", ""); // bosluk karakterini yok sayar	
		key = key.replace(" ", "");

		for (int i=0; i<(plaintext.length()); i++) {
			rankofkey = alphabet.indexOf(key.charAt(i % key.length())); // key'in "i" sirasindaki harfinin alfabedeki sirasi
			rankofplaintext = alphabet.indexOf(plaintext.charAt(i)); // plaintext'in "i" sirasindaki harfinin alfabedeki sirasi

			int sum = rankofkey + rankofplaintext;
			sum = sum % alphabet.length();

			ciphertext.append(alphabet.charAt(sum));
		}
	}

	public void setPlainText(String plaintext) {
		this.plaintext = plaintext;
	}

	public String getCipherText() {
		return this.ciphertext.toString();
	}
}