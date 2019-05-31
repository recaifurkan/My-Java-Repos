import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        SerialPort[] comPort = SerialPort.getCommPorts();
        for(SerialPort port:comPort){
            System.out.println(port.getSystemPortName());
        }
        Scanner sc = new Scanner(System.in);
        int selectedDevice = sc.nextInt();
        SerialPort port = comPort[selectedDevice];
        port.openPort();
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);


        port.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
            @Override
            public void serialEvent(SerialPortEvent event)
            {
                if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_WRITTEN)
                    System.out.println("All bytes were successfully transmitted!");

                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                byte[] newData = new byte[port.bytesAvailable()];
                int numRead = port.readBytes(newData, newData.length);
                String gelen = new String(newData);
                System.out.println("Read " + numRead + " bytes.");
                System.out.println( numRead);
            }
        });



        PrintWriter write = new PrintWriter(port.getOutputStream());
        while(true){
            write.write("recai");
            write.flush();

        }








    }
}
