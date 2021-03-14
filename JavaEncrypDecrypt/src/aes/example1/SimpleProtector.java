package aes.example1;

import java.security.*;
import javax.crypto.Cipher; 
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;
 
public class SimpleProtector {
 
    private static final String ALGORITHM = "AES";
    private static final String keyValueS ="ThisIsASecretKey";
 
    public static String encrypt(String valueToEnc) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValue = c.doFinal(valueToEnc.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encValue);
        return encryptedValue;
    }
 
    public static String decrypt(String encryptedValue) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue 
                = new BASE64Decoder().decodeBuffer(encryptedValue);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
 
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValueS.getBytes(), ALGORITHM);
        // SecretKeyFactory keyFactory 
        //              = SecretKeyFactory.getInstance(ALGORITHM);
        // key = keyFactory.generateSecret(new DESKeySpec(keyValue));
        return key;
    }
}
