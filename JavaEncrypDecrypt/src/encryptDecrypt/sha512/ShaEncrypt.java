package encryptDecrypt.sha512;

import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ShaEncrypt {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String orderID = "8";
        String price = "0.1";
        String currency = "TRY";
        String productName = "Test";
        String buyerName = "elektronik";
        String buyerPhone = "5388414584";
        String buyerEmail = "acayip@gmail.com";
        String buyerAdress = "Adres";
        String secret = "recai";
        String passwordToHash = orderID + price + currency + productName + buyerName + buyerPhone + buyerEmail + buyerAdress + secret;
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
        String base64Encoded = Base64.getEncoder().encodeToString(bytes);
        if("ryD+zs27mcluPnPT5ozPoKZEOcR2/hcDSofcoNMsNjM77dD3pkKsHDTi9RF5JEjY2sAvUg33kbI25v7Om6MIuA==".equals(base64Encoded)){
            System.out.println("Same");
        }
        else System.out.println("Not Same");
    }
}
