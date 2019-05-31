import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import javax.obex.ResponseCodes;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/*


bu sınıf ta internetten bulduğum güzel bi class direk pc üzeriden dosya göndrmeye yarıyor işe yarar
istiyosan b müzik deniyelim
 */
public class MyDiscoveryListener implements DiscoveryListener {

    private static Object lock = new Object();
    public ArrayList<RemoteDevice> devices;
    public ArrayList<String> urls;
    Scanner sc;

    public MyDiscoveryListener() {
        devices = new ArrayList<RemoteDevice>();
        urls = new ArrayList<>();
        sc = new Scanner(System.in);
    }


    public static void main(String[] args) throws IOException {


        listenerlaGonder();
    }

    private static void listenerlaGonder() {

        MyDiscoveryListener listener = new MyDiscoveryListener();

        try {
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            DiscoveryAgent agent = localDevice.getDiscoveryAgent();
            agent.startInquiry(DiscoveryAgent.GIAC, listener);

            try {
                synchronized (lock) {
                    lock.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }


            System.out.println("Device Inquiry Completed. ");


            UUID[] uuidSet = new UUID[1];
            uuidSet[0] = new UUID(0x1105); //OBEX Object Push service

            int[] attrIDs = new int[]{
                    0x0100 // Service name
            };

            for (RemoteDevice device : listener.devices) {
                agent.searchServices(
                        attrIDs, uuidSet, device, listener);


                try {
                    synchronized (lock) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }


                System.out.println("Service search finished.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendMessageToDevice(String serverURL) {
        try {
            System.out.println("Connecting to " + serverURL);

//            PrintWriter writer = new PrintWriter(Connector.openOutputStream(serverURL));
//            writer.write("recai");
//            writer.flush();

            ClientSession clientSession = (ClientSession) Connector.open(serverURL);
            HeaderSet hsConnectReply = clientSession.connect(null);
            if (hsConnectReply.getResponseCode() != ResponseCodes.OBEX_HTTP_OK) {
                System.out.println("Failed to connect");
                return;
            }
            File file = new File("F:\\WorkSpaces\\Java\\untitled\\src\\music.mp3");
            if(!file.exists()){
                System.exit(-1);
            }
            long boyut = file.length();

            HeaderSet hsOperation = clientSession.createHeaderSet();
            hsOperation.setHeader(HeaderSet.NAME, "music.mp3");

            hsOperation.setHeader(HeaderSet.LENGTH,boyut);
/*

şu anda dosya göndermeyi yapamadık ama çok yaklaştık
 */


            //Create PUT Operation
            Operation putOperation = clientSession.put(hsOperation);

            // Send some text to server
            System.out.println("Gönderiliyor");
            byte data[] = "Recai Furkan Bostancı".getBytes("UTF-8");
            OutputStream os = putOperation.openOutputStream();

            FileInputStream fis = new FileInputStream(file);



            int nRead;
            byte[] buffer = new byte[2000];

            while (  (nRead = fis.read(buffer)) != -1 ) {
                boyut =  boyut-nRead;
                System.out.println(boyut);
                os.write(nRead);
                os.flush();
            }
            os.close();
//            os.write(data);
//            os.close();

            putOperation.close();

            clientSession.disconnect(hsOperation);

            clientSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass arg1) {
        String name;
        try {
            name = btDevice.getFriendlyName(false);
        } catch (Exception e) {
            name = btDevice.getBluetoothAddress();
        }

        devices.add(btDevice);
        System.out.println("device found: " + name);

    }

    @Override
    public void inquiryCompleted(int arg0) {
        synchronized (lock) {
            lock.notify();
        }
    }

    @Override
    public void serviceSearchCompleted(int arg0, int arg1) {
        synchronized (lock) {
            lock.notify();
        }
    }

    private void listUrls() {
        for (String url : urls) {
            System.out.println(url);
        }
        int secenek = sc.nextInt();
        if (secenek >= 0 && secenek < urls.size())
            sendMessageToDevice(urls.get(secenek));


    }

    @Override
    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
        for (int i = 0; i < servRecord.length; i++) {
            String url = servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            if (url == null) {
                continue;
            }
            DataElement serviceName = servRecord[i].getAttributeValue(0x0100);
            if (serviceName != null) {
                System.out.println("service " + serviceName.getValue() + " found " + url);
                urls.add(url);
                listUrls();


                if (serviceName.getValue().equals("OBEX Object Push")) {

                }
            } else {
                System.out.println("service found " + url);
            }


        }
    }

}
