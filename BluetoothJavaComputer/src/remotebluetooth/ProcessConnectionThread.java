package remotebluetooth;

import javax.microedition.io.StreamConnection;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ProcessConnectionThread implements Runnable {

    // Constant that indicate command from devices
    private static final int EXIT_CMD = -1;
    private static final int KEY_RIGHT = 1;
    private static final int KEY_LEFT = 2;
    InputStream inputStream;
    Scanner sc;

    Robot robot;

    OutputStream os;
    PrintWriter writer;
    private StreamConnection mConnection;


    public ProcessConnectionThread(StreamConnection connection) {
        mConnection = connection;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {

            // prepare to receive data

            inputStream = mConnection.openInputStream();
            sc = new Scanner(inputStream);

            os = mConnection.openOutputStream();
            writer = new PrintWriter(os);

            /*
            streamlar ayarlandı out put streama print writer koyudlu input streama da scanner koyuldu text işlemi kolay oluyor
             */


            System.out.println("waiting for input");

            while (true) {

                /*
                burada sonsuz dögü şeklinde habire gelen yazı var mı diye baklıyor
                bu sayede gelen varsa alınıyor
                gönderilene bakmaya gerek yok çünkü onu istediğimiz zaman gönderirirz ama gelene hep bakmamaız gerekiyor
                zaten scanner gelirse alıyor yoksa döngü sürekli dönmüuor
                 */
                //int command = inputStream.read();

                if (sc.hasNext()) {
                    /*

                    burada scanner gelen yazı var  m ı diye bakıyor varsa geri bişey döndürüuor
                    yoksa habire bişey yollamıyor
                     */
                    String gelen = sc.nextLine();
                    System.out.println(gelen);
                    if (gelen.equals("recai + ")) {
                        writer.write("furkan + \n");
                        writer.flush();
                        /*
                        pc de kullanırken mutlaka flash kullanmalısın yoksa gitmez
                         */
                    }
                }


                int command = 1000;
                System.out.println(command);

                if (command == EXIT_CMD) {
                    System.out.println("finish process");
                    break;
                }

                /*
                burada işte yapan bişeyler belirlemiş gelen komuta göre mousu sağa sola kaydırıyor boş iş
                 */
                processCommand(command);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Process the command from client
     *
     * @param command the command code
     */
    private void processCommand(int command) {
        try {


            switch (command) {
                case KEY_RIGHT:
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    System.out.println("Right");
                    // release the key after it is pressed. Otherwise the event just keeps getting trigged
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    break;
                case KEY_LEFT:
                    robot.keyPress(KeyEvent.VK_LEFT);
                    System.out.println("Left");
                    /*
                    burada işte kendimize göre bişeyler göndermeye çalıştık ama çok  güzel çalışıyor
                     */
                    os.write(1);
                    System.out.println("Gonderildi");


                    // release the key after it is pressed. Otherwise the event just keeps getting trigged
                    robot.keyRelease(KeyEvent.VK_LEFT);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
