package main;

import java.io.File;
import java.io.IOException;

import java.net.Socket;
import java.util.Map;

import request.HttpRequest;
import response.HttpResponse;

public class JavaHTTPServer implements Runnable {

	// Client Connection via Socket Class
	private Socket connect;

	public JavaHTTPServer(Socket c) {
		connect = c;
	}

	@SuppressWarnings("unused")
	@Override
	public void run() {
		// we manage our particular client connection
		HttpRequest request = null;
		HttpResponse response = null;
		try {

			response = new HttpResponse(connect.getOutputStream());
			request = new HttpRequest(connect.getInputStream(), response);
			if (request == null && response == null) {
				if (Main.debug) {
					System.out.println("Ba�lant� olu�turulamad�");
					return;
				}
			}

			File requestFile = new File(Main.WEB_ROOT, request.getRequestPath());
			// istenen dosya var m� diye kontrol ediliyor
			if (requestFile.exists()) {
				// e�er anasayfa istenmi�se diye bak�l�yor

				if (request.getRequestPath().equals("/")) {
					// url de rfb diye parametreli de�i�ken g�nderilmi� mi  bak�l�yor
					String sonuc = (String) request.getParameter("rfb");

					response.sendText(sonuc);
//					response.sendAnasayfa();
				} else {
					response.sendFile(request.getRequestPath());
				}

			} else {
				try {
					response.fileNotFound(request.getRequestPath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}



		



	}
}
