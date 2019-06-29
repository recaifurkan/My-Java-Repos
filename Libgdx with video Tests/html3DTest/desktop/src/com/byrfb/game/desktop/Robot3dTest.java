package com.byrfb.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.byrfb.robot.Robot3d;


public class Robot3dTest {
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.vSyncEnabled = true;
		config.width = 800;
		config.height = 800;
		config.foregroundFPS = 120;
		config.backgroundFPS = 120;
//		config.fullscreen = true;
		
		new LwjglApplication(new Robot3d(), config);
	}

}
