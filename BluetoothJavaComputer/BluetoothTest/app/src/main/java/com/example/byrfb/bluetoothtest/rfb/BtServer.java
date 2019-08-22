package com.example.byrfb.bluetoothtest.rfb;

import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

public class BtServer extends Thread {
    static final UUID UUID = java.util.UUID.fromString("94c255b6-49d0-46cc-afd4-b593fde1250f");
    final String APPNAME = "MyBt";
    private BluetoothServerSocket serverSocket;


    public BtServer() {
        try {
            serverSocket = AnaSayfa.myBluetoothAdapter.listenUsingRfcommWithServiceRecord(APPNAME, UUID);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        BluetoothSocket socket = null;
        System.out.println("Server Açıldı");

        while (socket == null) {
            try {
                socket = serverSocket.accept();
                System.out.println("Servera bağlanıldı");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // bundan sonra server açık bağlan işte

        }
    }
}
