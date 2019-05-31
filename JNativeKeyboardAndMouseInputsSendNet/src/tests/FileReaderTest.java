package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import security.Encryption;

public class FileReaderTest {

	public static void main(String[] args) throws Exception {

		FileReaderTest test = new FileReaderTest();
		test.Sender();

	}

	public void Sender() {

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

	private String dosyayiOku(String path) throws FileNotFoundException, URISyntaxException {
		
		File urlDosyasi = new File(getClass().getResource(path).getFile());
		System.out.println(urlDosyasi.getAbsolutePath());
		
		StringBuilder urlBuilder = new StringBuilder();
		FileInputStream is = new FileInputStream(urlDosyasi);
		Scanner sc = new Scanner(is);

		while (sc.hasNext()) {
			urlBuilder.append(sc.next());
		}
		return urlBuilder.toString();

	}

}
