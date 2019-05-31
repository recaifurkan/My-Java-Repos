package main;

// Java implementation of  Server side 
// It contains two classes : Server and ClientHandler 
// Save file as Server.java 

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

// Server class 
public class Main {
	public static void main(String[] args) throws IOException {
		// server is listening on port 5056
		ServerSocket ss = new ServerSocket(8080);

		// running infinite loop for getting
		// client request
		while (true) {
			Socket s = null;

			try {
				// socket object to receive incoming client requests
				s = ss.accept();

				System.out.println("A new client is connected : " + s);

				// obtaining input and out streams
				InputStream dis = new DataInputStream(s.getInputStream());
				OutputStream dos = new DataOutputStream(s.getOutputStream());

				System.out.println("Assigning new thread for this client");

				// create a new thread object
				ClientHandler t = new ClientHandler(s, dis, dos);

				// Invoking the start() method
				t.start();
				t.write("Deneme");

			} catch (Exception e) {
				s.close();
				e.printStackTrace();
			}
		}
	}
}

// ClientHandler class 
class ClientHandler extends Thread {

	final InputStream dis;
	final OutputStream dos;
	final Socket s;
	PrintWriter writer;

	// Constructor
	public ClientHandler(Socket s, InputStream dis, OutputStream dos) {
		this.s = s;
		this.dis = dis;
		this.dos = dos;
		writer = new PrintWriter(dos);
		for(int i = 0 ; i<100;i++) {
			this.write("adasdsadsasads");
		}
	}

	@Override
	public void run() {

		Scanner br = new Scanner(dis);

		String received;

		while (true) {
			
			String str;
			while ((str = br.nextLine()) != null) {
				
				System.out.println(str);
				if (str.equals(""))
					break;

			}

		}
	}

	public void write(String text) {
		writer.write(text + "\n");
		writer.flush();

	}
}
