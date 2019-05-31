package security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import java.util.Scanner;

public class EncryptedFileReader {
	private String dosyayiOku(String path) throws FileNotFoundException, URISyntaxException {
		File urlDosyasi = new File(path);
		if (!urlDosyasi.exists()) {
			System.out.println("dosyanýn yanýndaki yok o yüzden class yanýndaki alýnýyor");

			urlDosyasi = new File(getClass().getResource(path).getFile());

		}

		StringBuilder urlBuilder = new StringBuilder();
		FileInputStream is = new FileInputStream(urlDosyasi);
		Scanner sc = new Scanner(is);

		while (sc.hasNext()) {
			urlBuilder.append(sc.next());
		}
		try {
			sc.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return urlBuilder.toString();

	}

	public String decryptFileContent(String path) {
		Encryption encrypt = new Encryption();
		String decrypted = null;
		try {
			decrypted = encrypt.decrypt(dosyayiOku(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decrypted;
	}

}
