package com.byrfb.output;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Output {


    PrintWriter out;


    public Output(OutputStream out) {
        this.out = new PrintWriter(out, true);


    }

    public void sendResponse(String sendingText) {
        out.write("HTTP/1.1 " + "200 OK");
        out.write("\r\n");
        out.write("\r\n");
        out.write(sendingText);
        out.write("\r\n");
        out.close();



    }

    public void sendOk(){
        out.println("HTTP/1.1 " + "200 OK\r\n");


    }

    public void sendRequest() {
        out.println(
                "GET / HTTP/1.1\r\n" +
                        "Host: " +
                        "intense-tor-33207.herokuapp.com" +
                        "\r\n");
    }

    public void sendText(String text) {
        out.println(text );

    }

    public void close() {
        out.close();
    }
}
