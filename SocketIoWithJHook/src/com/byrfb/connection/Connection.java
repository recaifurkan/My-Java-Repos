package com.byrfb.connection;

import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import com.byrfb.ComputerNameSettings.ComputerNameSolver;
import com.byrfb.main.Main;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/*
 * bu sýnýfýmýz socket io daki iþlemleri yamakta
 * socket io nun hazýrlanmasý baðlantýlarýn gelecekleri gideceklerin ayarlamalarýnýn yapýlmasý yapýlmakta
 * 
 * 
 */
public class Connection {
	static Socket socket;
	
	public Connection() {
		try {
			socket = IO.socket(Main.url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		socket.connect();
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

		  @Override
		  public void call(Object... args) {
			  JSONObject object = new JSONObject();
			  try {
				object.put("client", "program");
				object.put("computerName", ComputerNameSolver.computerName);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  socket.emit("connected", object);
		    socket.emit("foo", "hi");
		    System.out.println("connected");
		    
		  }

		}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

		  @Override
		  public void call(Object... args) {
			  System.out.println("disconnect");
		  }

		});
		
	}
	public static void send(String text) {
		socket.emit(Main.sendEvent, text);
	}

}
