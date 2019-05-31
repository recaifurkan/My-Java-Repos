package fileSend;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.DataInputStream;
import java.io.IOException;

public class FileSend {
    static String device_UUID = "btgoep://94350ABB9A50:12";

    private static void dataGonder() throws IOException {
        LocalDevice localDevice = LocalDevice.getLocalDevice();

        localDevice.setDiscoverable(DiscoveryAgent.GIAC); // Advertising the service

        String url = device_UUID;
        StreamConnectionNotifier server = null;
        while (server == null) {
            try {
                server = (StreamConnectionNotifier) Connector.open(url);

            } catch (Exception e) {
                e.printStackTrace();

            }

        }


        StreamConnection connection = server.acceptAndOpen(); // Wait until client connects
//=== At this point, two devices should be connected ===//
        DataInputStream dis = connection.openDataInputStream();

        char c;
        while (true) {
            c = dis.readChar();
            if (c == 'x')
                break;
        }

        connection.close();
    }


}
