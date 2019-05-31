package security;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EncryptedFileWriter {
	Encryption encryption = new Encryption();
	File messageTmp;
	FileOutputStream fos = null;
	PrintWriter writer = null;
	
	public EncryptedFileWriter(String path) {
		messageTmp = new File(path);
		
	}
	
	
	
	public void write(String message) {
		
		try {
			fos = new FileOutputStream(messageTmp,true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer = new PrintWriter(fos);
		String notEncryptedMessage = message.toString();
		String encryptedMessage = null;
		try {
			encryptedMessage = encryption.encrypt(notEncryptedMessage);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		writer.write(encryptedMessage);
		writer.close();
		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}



	

}
