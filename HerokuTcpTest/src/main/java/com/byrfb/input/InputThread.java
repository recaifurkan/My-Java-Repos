package com.byrfb.input;


import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class InputThread extends Thread {


    Scanner sc;
    Socket sock;

    Inputable inputable;


    public InputThread(Socket socket, Inputable inputable) throws IOException {
        this.sock = socket;
        this.sc = new Scanner(socket.getInputStream());
        this.inputable = inputable;

    }

    @Override
    public void run() {


        try {
            this.read();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void read() throws IOException {
        String clientGelen;


        while (true) {
            StringBuilder gelenVeri = new StringBuilder();
            if (sc.hasNext()) {
                if ((clientGelen = sc.nextLine()) != null) {
                    gelenVeri.append(clientGelen);
                }
            }
            else{
                break;

            }
            inputable.comingText(gelenVeri.toString());
        }


    }
}
