package com.byrfb.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.byrfb.libgdxgui.GuiDesign;

public class LibgdxGuiTest {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.vSyncEnabled = true;
		config.width = 400;
		config.height = 800;
		config.foregroundFPS = 120;
		config.backgroundFPS = 120;
//		config.fullscreen = true;

		new LwjglApplication(new GuiDesign(), config);
	}

}
