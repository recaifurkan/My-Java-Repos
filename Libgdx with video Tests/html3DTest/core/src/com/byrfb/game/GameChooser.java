package com.byrfb.game;

import com.badlogic.gdx.ApplicationListener;
import com.byrfb.bullettests.BulletTest;
import com.byrfb.libgdxgui.GuiDesign;

//import com.byrfb.gdxvideo.GdxVideoTest;


public class GameChooser {

	ApplicationListener game;

	public ApplicationListener getGame() {
		this.game = new BulletTest();
		return game;
	}

	public void setGame(ApplicationListener game) {
		this.game = game;
	}

}
