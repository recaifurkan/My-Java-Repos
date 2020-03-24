package com.byrfb.connection;

import com.byrfb.input.InputThread;
import com.byrfb.input.Inputable;
import com.byrfb.output.Output;

import java.io.IOException;
import java.net.Socket;

public class SocketConnection extends Thread implements Inputable {
    Socket clientSocket;


    Output o;
    InputThread it;

    public SocketConnection(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;


    }

    @Override
    public void run() {

        try {
            runSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void runSocket() throws IOException {

        o = new Output(clientSocket.getOutputStream());
        it = new InputThread(clientSocket, this);
        it.start();


    }

    @Override
    public void comingText(String text) {
        System.out.println("Clientten gelen : " + text);
        o.sendResponse("al Sana Yanıt");


    }
}
