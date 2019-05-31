package tests;

import security.EncryptedFileReader;

public class EncryptedFileReaderTest {
	public static void main(String[] args) {
		System.out.println(new EncryptedFileReader().decryptFileContent("message1.txt"));

	}

}
