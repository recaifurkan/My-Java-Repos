package com.byrfb.game;

import java.net.URISyntaxException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.byrfb.net.GetRequest;
import com.byrfb.socketio.ISocketIo;
import com.byrfb.socketio.tests.SocketIoLauncher;

public class ExtensionsTestMain extends Game {

	@Override
	public void create() {
		SocketIoLauncher.launch();

//		GetRequest.request("https://jsonplaceholder.typicode.com/posts");

	}

}
