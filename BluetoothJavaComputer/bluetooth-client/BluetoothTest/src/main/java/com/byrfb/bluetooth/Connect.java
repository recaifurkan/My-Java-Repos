package com.byrfb.bluetooth;

import org.korecky.bluetooth.client.hc06.BluetoothScanThread;
import org.korecky.bluetooth.client.hc06.RFCommClientThread;
import org.korecky.bluetooth.client.hc06.entity.RFCommBluetoothDevice;
import org.korecky.bluetooth.client.hc06.event.ErrorEvent;
import org.korecky.bluetooth.client.hc06.event.MessageReceivedEvent;
import org.korecky.bluetooth.client.hc06.event.ProgressUpdatedEvent;
import org.korecky.bluetooth.client.hc06.event.ScanFinishedEvent;
import org.korecky.bluetooth.client.hc06.listener.BluetoothScanEventListener;
import org.korecky.bluetooth.client.hc06.listener.RFCommClientEventListener;

import java.io.IOException;
import java.util.List;


public class Connect {
    static RFCommBluetoothDevice selectedDevice = null;

    public static void main(String[] args) throws IOException {


        // Prepare search thread
        BluetoothScanThread scanThread = new BluetoothScanThread(new BluetoothScanEventListener() {
            @Override
            public void error(ErrorEvent evt) {
                System.err.println(evt);

            }

            @Override
            public void scanFinished(ScanFinishedEvent evt) {
                List<RFCommBluetoothDevice> foundDevices = evt.getFoundDevices();
                selectedDevice = foundDevices.get(0);



            }

            @Override
            public void progressUpdated(ProgressUpdatedEvent evt) {


            }
        });

        // Start search of bluetooth device
        scanThread.start();
        scanThread.scanDevices();
        RFCommClientThread commThread = new RFCommClientThread(selectedDevice.getUrl(), '\n', null, new RFCommClientEventListener() {
            @Override
            public void error(ErrorEvent evt) {

            }

            @Override
            public void messageReceived(MessageReceivedEvent evt) {

            }
        });
        // Starts communication
        commThread.start();

        // Send message to HC06 module
        commThread.send("This is message for Arduino.");


    }
}
