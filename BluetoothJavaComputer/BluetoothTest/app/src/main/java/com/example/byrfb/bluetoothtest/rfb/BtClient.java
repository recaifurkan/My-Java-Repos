package com.example.byrfb.bluetoothtest.rfb;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;

public class BtClient extends Thread {
    BluetoothSocket socket;
    BluetoothDevice device;

    public BtClient(BluetoothDevice device) {
        this.device = device;
        try {
            socket = device.createRfcommSocketToServiceRecord(BtServer.UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        //AnaSayfa.myBluetoothAdapter.cancelDiscovery();
        boolean baglandi = false;
        while (!baglandi) {
            try {
                socket.connect();
                baglandi = true;
                // bağlandıktan sonraki fantazi
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
