package com.byrfb.game;

import com.badlogic.gdx.ApplicationListener;

import com.byrfb.bullet3dcontacttests.BulletTest;
import com.byrfb.bullet3dcontacttests.BulletTest1;
//import com.byrfb.gdxvideo.GdxVideoTest;

import visui.test.TestApplication;

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
