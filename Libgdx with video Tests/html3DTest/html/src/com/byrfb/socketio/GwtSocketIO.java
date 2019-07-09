package com.byrfb.socketio;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public class GwtSocketIO implements ISocketIo {

	private Object socketIO = null;
	private int delayTime = 200;

	public GwtSocketIO() {

	}

	public Object getSocket() {
		return socketIO;
	}////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public interface SocketAndResponseHandler<T, TT> extends EventHandler {

		public void onSocketAndResponse(T data, ResponseHandler<TT> respuesta);

	}//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private static <T> void onSocketMiddleware(T data, SocketHandler<T> callback) {

		callback.onSocket(data);
	}

	private static <T, TT> void onSocketMiddlewareResponse(T data, SocketAndResponseHandler<T, TT> callback,
			final Object res) {

		callback.onSocketAndResponse(data, new ResponseHandler<TT>() {

			@Override
			public void onResponse(TT respuesta) {
				// TODO Auto-generated method stub
				respuesta(res, respuesta);
			}
		});
	}

	private static native <TT> void respuesta(Object res, TT respuesta) /*-{

		res(respuesta);

	}-*/;///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private static <T> void onEmitMiddleware(T data, SocketHandler<T> callback) {

		callback.onSocket(data);
	}

	public void connect() {

		socketIO = connectSocketIONative(false);

	}///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void connect(final String path) {

		ScriptInjector.fromUrl("/socket.io/socket.io.js").setCallback(new Callback<Void, Exception>() {

			@Override
			public void onSuccess(Void result) {

				socketIO = connectSocketIONative(path, true);

			}

			@Override
			public void onFailure(Exception reason) {
				// TODO Auto-generated method stub

			}

		}).inject();

//		while(isLoaded != true) ;

	}///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void connect(String path, boolean forceNewConnection) {

		socketIO = connectSocketIONative(path, forceNewConnection);

	}///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private native Object connectSocketIONative(boolean forceNewConnection)/*-{

		var io_tmp = $wnd.io || io;

		var socket = io_tmp.connect({
			transports : [ 'websocket' ],
			'force new connection' : forceNewConnection
		});//solo websocket

		return socket;
	}-*/;///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private native Object connectSocketIONative(String path, boolean forceNewConnection)/*-{

		var io_tmp = $wnd.io || io;

		var socket = io_tmp.connect(path, {
			transports : [ 'websocket' ],
			'force new connection' : forceNewConnection
		});//solo websocket

		return socket;
	}-*/;///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void disconnect() {
		if (socketIO == null)
			return;

		disconnectSocketIONative(socketIO);
	}///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private native void disconnectSocketIONative(Object socket)/*-{

		socket.disconnect();

	}-*/;///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public <T> void onSocket(final String servicio, final SocketHandler<T> callback) {

		if (socketIO == null) {
			Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {
				@Override
				public boolean execute() {
					onSocket(servicio, callback);
					return false;
				}
			}, delayTime);
			return;
		}

		final Object listener = onSocketNative(socketIO, servicio, callback);

		HandlerRegistration registration = new HandlerRegistration() {
			@Override
			public void removeHandler() {
				onSocketRemoveNative(socketIO, servicio, listener);
			}
		};

	}

	/**
	 * socket.on(nombre,function(data){});
	 * 
	 * @param servicio
	 * @param callback
	 */
	private native <T> Object onSocketNative(Object socket, String servicio, SocketHandler<T> callback) /*-{

		function listener(data) {

			$entry(@com.byrfb.socketio.GwtSocketIO::onSocketMiddleware(*)(data,callback));

		}
		socket.on(servicio, listener);

		return listener;

	}-*/;////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Removes el evento
	 */
	private native void onSocketRemoveNative(Object socket, String servicio, Object listener) /*-{

		socket.removeListener(servicio, listener);

	}-*/;////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public <T, TT> HandlerRegistration onSocket(final String servicio, SocketAndResponseHandler<T, TT> callback) {
		if (socketIO == null)
			return null;

		final Object listener = onSocketNative(socketIO, servicio, callback);

		HandlerRegistration registration = new HandlerRegistration() {
			@Override
			public void removeHandler() {
				onSocketRemoveNative(socketIO, servicio, listener);
			}
		};

		return registration;
	}

	/**
	 * socket.on(nombre,function(data,respuesta){});
	 * 
	 * @param servicio
	 * @param callback
	 */
	private native <T, TT> Object onSocketNative(Object socket, String servicio,
			SocketAndResponseHandler<T, TT> callback) /*-{

		function listener(data, response) {

			$entry(@com.byrfb.socketio.GwtSocketIO::onSocketMiddlewareResponse(*)(data,callback,response));

		}
		socket.on(servicio, listener);

		return listener;

	}-*/;///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public <T> void emitSocket(final String servicio, final T data) {
		if (socketIO == null) {
			Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {
				@Override
				public boolean execute() {
					emitSocket(servicio, data);
					return false;
				}
			}, delayTime);
			return;
		}
		emitSocketNative(socketIO, servicio, data);

	}//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * socket.emit(nombre,data);
	 * 
	 * @param servicio
	 * @param callback
	 */
	private native <T> void emitSocketNative(Object socket, String servicio, T data) /*-{

		socket.emit(servicio, data);

	}-*/;///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public <T, TT> void emitSocketResponse(String servicio, T data, SocketHandler<TT> callback) {
		if (socketIO == null)
			return;
		emitSocketReponseNative(socketIO, servicio, data, callback);

	}//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * socket.emit(nombre,data,function(respuesta){});
	 * 
	 * @param servicio
	 * @param callback
	 */
	private native <T, TT> void emitSocketReponseNative(Object socket, String servicio, T data,
			SocketHandler<TT> callback) /*-{

		socket
				.emit(
						servicio,
						data,
						function(response) {

							$entry(@com.byrfb.socketio.GwtSocketIO::onEmitMiddleware(*)(response,callback));

						})
	}-*/;///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public <T> String getStringData(T data) {
		return (String) data;
	}

}
