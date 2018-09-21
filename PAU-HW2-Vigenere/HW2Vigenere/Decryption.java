/*
	NOT: Bu program IDE kullanilmadan yazilmistir.
	Testleri elle derlenerek, java-9-openjdk ile yapilmistir.

	Lütfen proje icindeki README.md dosyasini okuyunuz..
*/
public class Decryption{
	public final String alphabet = "abcçdefgğhıijklmnoöprsştuüvyz";
	public static String ciphertext; // sifrelenmis metin
	public static StringBuilder plaintext = new StringBuilder("");

	public void decrypt(String key){
		int rankofciphertext;
		int rankofkey;

		plaintext = new StringBuilder(""); // plaintext'in icini temizler
		ciphertext = ciphertext.replace(" ", ""); // bosluk karakterini yok sayar	
		key = key.replace(" ", "");

		for (int i=0; i<(ciphertext.length()); i++) {
			rankofkey = alphabet.indexOf(key.charAt(i % key.length())); // key'in "i" sirasindaki harfinin alfabedeki sirasi
			rankofciphertext = alphabet.indexOf(ciphertext.charAt(i)); // ciphertext'in "i" sirasindaki harfinin alfabedeki sirasi

			int sum = rankofciphertext - rankofkey;
			if(sum < 0)
				sum += alphabet.length();
			sum = sum % alphabet.length();

			plaintext.append(alphabet.charAt(sum));
		}
	}

	public void setCipherText(String ciphertext) {
		this.ciphertext = ciphertext;
	}

	public String getPlainText() {
		return this.plaintext.toString();
	}
}