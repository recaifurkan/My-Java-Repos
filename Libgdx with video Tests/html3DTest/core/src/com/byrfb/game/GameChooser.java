package com.byrfb.game;

import com.badlogic.gdx.ApplicationListener;
import com.bulletphysics.test.bullet3dcontacttests.BulletTest;
import com.byrfb.libgdxgui.GuiDesign;

//import com.byrfb.gdxvideo.GdxVideoTest;


public class GameChooser {

	ApplicationListener game;

	public ApplicationListener getGame() {
		this.game = new ExtensionsTestMain();
		return game;
	}

	public void setGame(ApplicationListener game) {
		this.game = game;
	}

}
