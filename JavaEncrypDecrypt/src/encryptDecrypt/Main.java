package encryptDecrypt;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
public class Main {
	Cipher cipher;
	String key = "1234567812345678"; // 128 bit key
	Key aesKey;
	public Main() {
		aesKey = new SecretKeySpec(key.getBytes(), "AES");
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private String encrypt(String text) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		
		// encrypt the text
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        return new String(encrypted);
		
	}
	
	private String decrypt(String encrypted) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		byte[] encrypted1 = encrypted.getBytes("UTF-8");
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String decrypted = new String(cipher.doFinal(encrypted1),"UTF-8");
        return decrypted;
		
	}
	
	

	public static void main(String[] args) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Main main = new Main();
		Scanner sc = new Scanner(System.in);
		while(true) {
			String encrypted = main.encrypt(sc.nextLine());
			System.out.println(encrypted);
			String decrypted = main.decrypt(encrypted);
			System.out.println(decrypted);
		}

	}

}
