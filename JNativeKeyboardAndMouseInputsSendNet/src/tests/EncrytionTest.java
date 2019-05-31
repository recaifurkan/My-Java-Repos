package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


import security.Encryption;
public class EncrytionTest {
	public static void main(String[] args) throws Exception {
		Encryption main = new Encryption();
		Scanner sc = new Scanner(System.in);
		while(true) {
			String encrypted = main.encrypt(sc.nextLine());
			System.out.println(encrypted);
			String decrypted = main.decrypt(encrypted);
			System.out.println(decrypted);
		}

	}
	
	public void  Sender() {

		Encryption encrypt = new Encryption();
		String url = null;

		try {
			 url = encrypt.decrypt(dosyayiOku("url.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(url);

	}

	private String dosyayiOku(String path) throws FileNotFoundException {
		File urlDosyasi = new File(path);
		StringBuilder urlBuilder = new StringBuilder();
		FileInputStream is = new FileInputStream(urlDosyasi);
		Scanner sc = new Scanner(is);

		while (sc.hasNext()) {
			urlBuilder.append(sc.next());
		}
		return urlBuilder.toString();

	}
	
	
}
	
	

	


