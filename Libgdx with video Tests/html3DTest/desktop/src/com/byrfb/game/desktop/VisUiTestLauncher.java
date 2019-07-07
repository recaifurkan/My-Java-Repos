package com.byrfb.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import visui.test.TestApplication;

public class VisUiTestLauncher {

	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "VisUI test application";
		config.width = 800;
		config.height = 800;
		new LwjglApplication(new TestApplication(), config);
	}

}