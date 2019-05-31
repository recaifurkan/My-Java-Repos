package com.rfbsoft.Serve;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.util.Scanner;

public class Clie {
	public Clie() {
		Client client = new Client();

		new Thread(client).start();

		try {
			client.connect(5000, "localhost", 54555, 54777);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.addListener(new Listener() {
			public void connected(Connection connection) {
				connection.sendTCP("Furkan");

			}

			public void received(Connection connection, Object object) {
				connection.sendTCP(object);
				// System.out.println(object);

			}
		});
		Scanner sc = new Scanner(System.in);
		while (true) {
			client.sendTCP(sc.nextLine());
		}

	}

}
