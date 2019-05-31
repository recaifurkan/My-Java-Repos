
/**
 * bu sýnýfla display için gerekli ayarlar yapýlýp render iþlemleri falan ayarlnýypr
 * fps delta time gibi bilgiler buradan alýnabilir
 * 
 * 
 */
package com.byrfb.lwjgl;

import com.byrfb.lwjgl.interfaces.IGame;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.awt.*;

public class DisplayManager {
	Canvas canvas;
	IGame game;

	/**
	 * time at last frame
	 */
	private long lastFrame;

	/**
	 * frames per second
	 */
	private int fpsCounter;
	
	
	
	private int fps;
	/**
	 * last fps time
	 */
	private long lastFPS;
	/**
	 * delta time
	 */
	private int deltaTime;

	public DisplayManager(Canvas canvas, IGame game) {
		this.canvas = canvas;
		this.game = game;
		try {
			setupAndRun();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public DisplayManager(IGame game) {
		try {
			setupAndRun();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getFps() {
		return fpsCounter;
	}

	public void setDeltaTime(int delta) {
		this.deltaTime = delta;
	}

	public void setupAndRun() throws LWJGLException {
		this.init();
		this.loop();
		this.destroy();

	}

	public void init() throws LWJGLException {
		if (canvas != null)
			Display.setParent(canvas);
		Display.setVSyncEnabled(true);

		Display.setDisplayMode(new DisplayMode(canvas.getWidth(), canvas.getHeight()));
		System.out.println(Display.getWidth());
		Display.create();
		game.create();

	}

	public void loop() {
		while (!Display.isCloseRequested()) {
			getDelta();

			game.update();
			game.render();
			pollInput();
			Display.update();
			updateFPS(); // update FPS Counter
			Display.sync(60);
//			System.out.println(fps);

		}

	}
	
	/*
	 * 
	 * 
	 * allatki metotta mouse sadece open gl pencerisi için çalýþýyor ancak keyboard bütün uyguama için kullanýlabilmekte
	 */

	public void pollInput() {

		if (Mouse.isButtonDown(0)) {
			int x = Mouse.getX();
			int y = Mouse.getY();

			System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			System.out.println("SPACE KEY IS DOWN");
		}

		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					System.out.println("A Key Pressed");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					System.out.println("S Key Pressed");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					System.out.println("D Key Pressed");
				}
			} else {
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					System.out.println("A Key Released");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					System.out.println("S Key Released");
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					System.out.println("D Key Released");
				}
			}
		}
	}

	public void destroy() {
		Display.destroy();
		game.destroy();
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 *
	 * @return milliseconds passed since last frame
	 */
	public void getDelta() {
		long time = getTime();
		deltaTime = (int) (time - lastFrame);
		lastFrame = time;

	}

	/**
	 * Get the accurate system time
	 *
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		long time = getTime();
		if (time - lastFPS > 1000) {
			fps = fpsCounter;
			Display.setTitle("FPS: " + fpsCounter);
//            System.out.println("FPS: " + fps);
			fpsCounter = 0;
			lastFPS = time;

		}
		fpsCounter++;
	}

}
