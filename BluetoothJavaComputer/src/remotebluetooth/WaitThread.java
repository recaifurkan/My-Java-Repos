package remotebluetooth;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.IOException;

/*

kardeş bura threaddir

 */

public class WaitThread implements Runnable {

    /**
     * Constructor
     */
    public WaitThread() {
    }
/*
threadi başlatan sınıf burası gördüğün gibi işlem yapan metodu çağrıyor
 */
    @Override
    public void run() {
        waitForConnection();
    }

    /**
     * Waiting for connection from devices
     */
    private void waitForConnection() {
        // retrieve the local Bluetooth device object
        LocalDevice local = null;

        StreamConnectionNotifier notifier;
        StreamConnection connection = null;

        // setup the server to listen for connection
        try {
            local = LocalDevice.getLocalDevice(); // local bluetooth cihazı varsa onu getiriyor
            local.setDiscoverable(DiscoveryAgent.GIAC);

            UUID uuid = new UUID("04c6093b00001000800000805f9b34fb", false);
            // direk buna bağlanmasının sebebi android cihazda da uuid olarak bu üsttekini vermiş olmamız o yüzden başka bişey yapmaya gerek kalmıyor direk buna bağlanıyort
            System.out.println(uuid.toString());

            String url = "btspp://localhost:" + uuid.toString() + ";name=RemoteBluetooth";
            notifier = (StreamConnectionNotifier) Connector.open(url);
        } catch (BluetoothStateException e) {
            System.out.println("Bluetooth is not turned on.");
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // waiting for connection
        while (true) {
            try {
                System.out.println("waiting for connection...");
                connection = notifier.acceptAndOpen(); // burada connection oluştu oluşan connecitini da process connection threada gönderiyor

                Thread processThread = new Thread(new ProcessConnectionThread(connection));
                processThread.start();

            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
