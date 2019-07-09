package com.byrfb.socketio.nativesocket;

import java.net.URISyntaxException;

import com.byrfb.socketio.ISocketIo;
import com.byrfb.socketio.SocketHandler;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.emitter.Emitter.Listener;
import com.github.nkzawa.socketio.client.IO;





public class NativeSocketIo implements ISocketIo {

	private Socket socket;


	@Override
	public void connect(String path) {
		
		try {
			socket = IO.socket(path);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		socket.connect();

		
		
	}

	@Override
	public <T> void onSocket(String servicio, final SocketHandler<T> callback) {
		socket.on(servicio, new Emitter.Listener() {
			
			@Override
			public void call(Object... args) {
				callback.onSocket((T) args);
				
			}
		});
		
	}

	@Override
	public <T> void emitSocket(String servicio, T data) {
		socket.emit(servicio, data);
		
	}

	@Override
	public <T> String getStringData(T data) {
		Object[] dataObject = (Object[]) data; 
		String gelenData = (String) dataObject[0];
		return gelenData;
	}




}
