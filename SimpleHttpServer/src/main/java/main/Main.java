package main;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

public class Main {

	public static final File WEB_ROOT = new File("public_html");
	static final String DEFAULT_FILE = "index.html";
	public static final String FILE_NOT_FOUND = "404.html";
	public static final String METHOD_NOT_SUPPORTED = "not_supported.html";

	// port to listen connection
	static final int PORT = 8080;

	// verbose mode
	public static final boolean debug = true;

	public static void main(String[] args) {
		try {
			ServerSocket serverConnect = new ServerSocket(PORT);
			System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");

			System.out.println(serverConnect.getInetAddress().getHostAddress());
			// we listen until user halts server execution
			while (true) {
				JavaHTTPServer myServer = new JavaHTTPServer(serverConnect.accept());

				if (debug) {
					System.out.println("Connecton opened. (" + new Date() + ")");
				}

				// create dedicated thread to manage the client connection
				Thread thread = new Thread(myServer);
				thread.start();
			}

		} catch (IOException e) {
			System.err.println("Server Connection error : " + e.getMessage());
		}
	}

}
