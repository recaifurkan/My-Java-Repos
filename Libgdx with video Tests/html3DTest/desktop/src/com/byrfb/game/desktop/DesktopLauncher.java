package com.byrfb.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.byrfb.game.GameChooser;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.vSyncEnabled = true;
		config.width = 800;
		config.height = 800;
		config.foregroundFPS = 120;
		config.backgroundFPS = 120;
//		config.fullscreen = true;

		new LwjglApplication(new GameChooser().getGame(), config);
	}
}
