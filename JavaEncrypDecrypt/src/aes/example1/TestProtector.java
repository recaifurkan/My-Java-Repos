package aes.example1;

public class TestProtector {
 
    public static void main(String[] args) throws Exception {
        String password = "mypassword";
        String salt = "this is a simple clear salt";
        String passwordEnc = Protector.encrypt(password, salt);
        String passwordDec = Protector.decrypt(passwordEnc, salt);
 
        System.out.println("Salt Text : " + salt);
        System.out.println("Plain Text : " + password);
        System.out.println("Encrypted : " + passwordEnc);
        System.out.println("Decrypted : " + passwordDec);
    }
}