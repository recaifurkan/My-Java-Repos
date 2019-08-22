package com.example.byrfb.bluetoothtest.rfb;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SendReceiveThread extends Thread {
    private BluetoothSocket socket = null;
    private InputStream is = null;
    private OutputStream os = null;

    public SendReceiveThread(BluetoothSocket socket) throws IOException {
        this.socket = socket;

        is = socket.getInputStream();
        os = socket.getOutputStream();

    }

    public void run() {
        byte[] buffer = new byte[1024];
        int bytes;
        while (true) {
            try {
                bytes = is.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(String s) {

        try {
            os.write(s.getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
