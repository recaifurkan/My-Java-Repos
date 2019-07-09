package com.byrfb.socketio;


public interface ISocketIo {
	
	public void connect(String path);
	
	public <T> void onSocket(final String servicio,SocketHandler<T> callback);
	
	public <T>void emitSocket(String servicio,T data);
	
	public <T> String getStringData(T data);

	
	

}




