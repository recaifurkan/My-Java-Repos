package com.byrfb.socketio.tests;

import java.net.URISyntaxException;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.byrfb.jsLibraries.Library;
import com.byrfb.socketio.ISocketIo;
import com.byrfb.socketio.SocketEvent;
import com.byrfb.socketio.SocketHandler;
import com.byrfb.socketio.nativesocket.NativeSocketIo;

public class SocketIoLauncher {

	public static ISocketIo socket;

	public static String PORT = "8000";
	public static String PATH = "http://localhost:" + PORT;

	public static <T> void launch() {
		Class clazz = null;

		if (Gdx.app.getType() == ApplicationType.WebGL) {
			/*
			 * web gl i�in socket atamas� direk html launcher da yap�l�yor
			 * 
			 * 
			 */

		} else {
			try {
				clazz = ClassReflection.forName("com.byrfb.socketio.nativesocket.NativeSocketIo");
				socket = (ISocketIo) ClassReflection.newInstance(clazz);
			} catch (ReflectionException e) {
				System.out.println("Socket io native y�klenme hatas�");
			}

		}

		socket.connect(PATH);

		socket.emitSocket("send", "denemeG�nderilen");

		socket.onSocket(SocketEvent.EVENT_CONNECT, new SocketHandler<T>() {

			@Override
			public void onSocket(T data) {

				System.out.println("ba�land�");
//				Library.consoleLog("ba�land�");

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
