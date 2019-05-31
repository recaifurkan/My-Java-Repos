package tests;

import java.util.Scanner;

import security.EncryptedFileWriter;

public class EncryptedFileWriterTest {

	public static void main(String[] args) {
		EncryptedFileWriter writer = new EncryptedFileWriter("message.txt");
		Scanner sc = new Scanner(System.in);
		StringBuilder text = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			text.append(sc.nextLine() + "\n");
		}
		writer.write(text.toString());

	}

}
