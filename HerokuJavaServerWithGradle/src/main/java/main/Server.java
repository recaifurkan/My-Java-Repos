package main;

import request.HttpRequest;
import response.HttpResponse;
import router.Router;

import java.io.IOException;
import java.net.Socket;

public class Server implements Runnable {
    Socket socket;

    Server(Socket csocket) {
        this.socket = csocket;
    }

    @Override
    public void run() {
        if(!socket.isConnected())return;
        HttpResponse response = null;
        try {
            response = new HttpResponse(socket.getOutputStream());
            HttpRequest request = new HttpRequest(socket.getInputStream(), response);
            Router router = new Router(request,response);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}


//    public void run() {
//        try {
//
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//
//
//            String s;
//            while ((s = in.readLine()) != null) {
//                System.out.println(s);
//                if (s.isEmpty()) {
//                    break;
//                }
//            }
//
//
//            out.write("HTTP/1.0 200 OK\r\n");
//            out.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
//            out.write("main.Server: Apache/0.8.4\r\n");
//            out.write("Content-Type: text/html\r\n");
//            out.write("Content-Length: 59\r\n");
//            out.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
//            out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
//            out.write("\r\n");
//            out.write("<TITLE>Exemple</TITLE>");
//
//            out.write("<P>Hello with heroku java server</P>");
//
//            out.write("\r\n");
//
//
//            out.close();
//            in.close();
//            socket.close();
//            LOG("SOCKET KAPANDI");
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }