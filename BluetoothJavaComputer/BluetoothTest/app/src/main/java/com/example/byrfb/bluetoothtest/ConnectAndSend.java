package com.example.byrfb.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

public class ConnectAndSend {
    private static final UUID MY_UUID = UUID.fromString("04c6093b-0000-1000-8000-00805f9b34fb");
    public ConnectThread.ConnectedThread th2;
    public ConnectThread th1;
    private BluetoothAdapter mBtAdapter;

    public ConnectAndSend() {
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {

            for (BluetoothDevice device : pairedDevices) {
                System.out.println(device);

                th1 = new ConnectThread(device);
                th1.start();


            }
        } else {

        }


    }

    class ConnectThread extends Thread {
        private final BluetoothDevice mmDevice;
        private BluetoothSocket mmSocket = null;

        public ConnectThread(BluetoothDevice device) {
            mmDevice = device;
            BluetoothSocket tmp = null;

            // Get a BluetoothSocket for a connection with the
            // given BluetoothDevice
            try {
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {

            }
            mmSocket = tmp;
        }

        public void run() {

            setName("ConnectThread");

            // Always cancel discovery because it will slow down a connection


            // Make a connection to the BluetoothSocket
            try {
                // This is a blocking call and will only return on a
                // successful connection or an exception
                mmSocket.connect();

                th2 = new ConnectedThread(mmSocket);
                th2.start();
            } catch (IOException e) {

                // Close the socket
                try {
                    mmSocket.close();
                } catch (IOException e2) {

                }
                // Start the service over to restart listening mode


            }
        }

        public class ConnectedThread extends Thread {
            private final BluetoothSocket mmSocket;
            private final InputStream mmInStream;
            private final OutputStream mmOutStream;
            Scanner sc;

            public ConnectedThread(BluetoothSocket socket) {

                mmSocket = socket;
                InputStream tmpIn = null;
                OutputStream tmpOut = null;

                // Get the BluetoothSocket input and output streams
                try {
                    tmpIn = socket.getInputStream();
                    tmpOut = socket.getOutputStream();
                } catch (IOException e) {

                }

                mmInStream = tmpIn;
                mmOutStream = tmpOut;


            }

            public void run() {

                byte[] buffer = new byte[1024];
//                InputStreamReader reader = new InputStreamReader(mmInStream);

                Scanner sc = new Scanner(mmInStream);
                // Keep listening to the InputStream while connected
                while (true) {
                    // Read from the InputStream
                    //System.out.println("rfb");

                    if (sc.hasNext()) {
                        System.out.println(sc.nextLine());
                    }
//                    try {
//                        if(mmInStream.available()==1){
//                            System.out.println(mmInStream.read());
//
//
//                        }
////                        System.out.println(reader.read());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }


//                        int gelen = mmInStream.read();


//
                    // Send the obtained bytes to the UI Activity

                }
            }

            /**
             * Write to the connected OutStream.
             *
             * @param buffer The bytes to write
             */
            public void write(byte[] buffer) {
                try {
                    mmOutStream.write(buffer);

                    // Share the sent message back to the UI Activity
//                mHandler.obtainMessage(BluetoothChat.MESSAGE_WRITE, -1, -1, buffer)
//                        .sendToTarget();
                } catch (IOException e) {

                }
            }

            public void write(int out) {
                //                    mmOutStream.write(out);
                PrintWriter writer = new PrintWriter(mmOutStream);
                writer.write("recai + \n");
                writer.flush();

                // Share the sent message back to the UI Activity
//                mHandler.obtainMessage(BluetoothChat.MESSAGE_WRITE, -1, -1, buffer)
//                        .sendToTarget();
            }

            public void cancel() {
                try {

                    mmSocket.close();
                } catch (IOException e) {

                }
            }
        }
    }
}
