package main;




import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Bu projede herokuya java serve etmeyi güzel bir şekşlde öğrendin ve uyguladın
 * portunu procfile ye yazıyorsun
 * sonraında önceki yazdığınla aynı şekilde yalnız java da serve ederken
 * headerlarla content arasında bir tane new line karakteri kullansan aolur ama heroku da bu işke  böyle olmuyor
 * heroku için
 * \r\n kullanman lazım
 * bi de bitiminde de \r\n kullanman lazım yoksa http bitişi olarak algılamayıp hatra ver,yor heroku
 * bu proje ile çok iyi bir şekilde öğprenmiş oldun
 *
 *
 *
 *
 */
public class Main {


    public static boolean debug = true;
    public static final File WEB_ROOT = new File("public_html");
    static final String DEFAULT_FILE = "index.html";
    public static final String FILE_NOT_FOUND = "404.html";
    public static final String METHOD_NOT_SUPPORTED = "not_supported.html";


    public static void main(String args[]) throws Exception {
        int port = 80;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {

        }

//       new JarDependencieTest();




        ServerSocket ssock = new ServerSocket(port);
        System.out.println("Listening");

        while (true) {
            Socket sock = ssock.accept();
            LOG("sOCKET KABUL EDİLDİ");
            new Thread(new Server(sock)).start();
        }
    }


    public static void LOG(String log) {
        System.out.println(log);
    }
}