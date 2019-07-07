package com.byrfb.game;

import com.badlogic.gdx.Game;

import com.byrfb.net.GetRequest;

public class ExtensionsTestMain extends Game {
	@Override
	public void create() {
		GetRequest.request("https://jsonplaceholder.typicode.com/posts");

	}

}
