package com.byrfb.socketio.tests;

import com.byrfb.platformsbridges.PlatformsBridge;
import com.byrfb.socketio.ISocketIo;
import com.byrfb.socketio.SocketEvent;
import com.byrfb.socketio.SocketHandler;


public class SocketIoLauncher {

	public static ISocketIo socket;

	public static String PORT = "8000";
	public static String PATH = "http://localhost:" + PORT;

	public static <T> void launch() {
		
		socket = PlatformsBridge.socket;

		socket.connect(PATH);

		socket.emitSocket("send", "denemeGönderilen");

		socket.onSocket(SocketEvent.EVENT_CONNECT, new SocketHandler<T>() {

			@Override
			public void onSocket(T data) {

				System.out.println("baðlandý");
//				Library.consoleLog("baðlandý");

			}
		});

		socket.onSocket("send", new SocketHandler<T>() {

			@Override
			public void onSocket(T data) {
				String gelenData = socket.getStringData(data);
//				Library.consoleLog(gelenData);
				System.out.println(gelenData);

			}
		});

	}

}
