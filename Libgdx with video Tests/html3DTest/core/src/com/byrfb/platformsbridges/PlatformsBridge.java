package com.byrfb.platformsbridges;

import com.badlogic.gdx.Gdx;
import com.byrfb.jsLibraries.Library;
import com.byrfb.socketio.ISocketIo;

public class PlatformsBridge {
	
	public static ISocketIo socket;
	
	public static void debug(String text) {
		switch (Gdx.app.getType()) {		
		case WebGL:
			Library.consoleLog(text);
			break;
		default:
			System.out.println(text);
			break;
		
		}
	}

}
