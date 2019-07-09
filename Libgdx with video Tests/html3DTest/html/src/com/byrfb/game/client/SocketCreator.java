//package com.byrfb.game.client;
//
//
//import com.byrfb.jsLibraries.Library;
//import com.byrfb.socketio.GwtSocketIO;
//import com.byrfb.socketio.GwtSocketIO.SocketHandler;
//import com.google.gwt.core.client.Callback;
//
//import com.google.gwt.core.client.ScriptInjector;
//
//public class SocketCreator {
//	
//	public static String PORT = "8000";
//	
//
//	public static void create() {
//		ScriptInjector.fromUrl("/socket.io/socket.io.js").setCallback(new Callback<Void, Exception>() {
//
//			@Override
//			public void onSuccess(Void result) {
//				// TODO Auto-generated method stub
//				final GwtSocketIO socket = GwtSocketIO.connect("http://localhost:" + PORT) ;
//				socket.onSocket("send", new SocketHandler<String>() {
//
//					@Override
//					public void onSocket(String data) {
//						// TODO Auto-generated method stub
//						Library.consoleLog(data);
//						Library.consoleLog("Balantý baþarýlý");
//						socket.emitSocket("my event", data + " new");
////						socket.emitSocket(servicio, data);
//					}
//				});
//
//			}
//
//			@Override
//			public void onFailure(Exception reason) {
//				// TODO Auto-generated method stub
//
//			}
//
//		}).inject();
//	}
//
//}
