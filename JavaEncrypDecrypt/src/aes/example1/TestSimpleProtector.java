package aes.example1;

public class TestSimpleProtector {
 
    public static void main(String[] args) throws Exception {
 
        String password = "mypassword";
        String passwordEnc = SimpleProtector.encrypt(password);
        String passwordDec = SimpleProtector.decrypt(passwordEnc);
 
        System.out.println("Plain Text : " + password);
        System.out.println("Encrypted : " + passwordEnc);
        System.out.println("Decrypted : " + passwordDec);
    }
}