package com.byrfb.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bulletphysics.test.HelloWorld;
import com.byrfb.jsLibraries.Library;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Music music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.play();
		img = new Texture("badlogic.jpg");
		Library lib = new Library(); 
//		lib.initAmmo();
//		lib.consoleLog(String.valueOf(lib.topla(5, 6)));
		HelloWorld.run();
		
		
		

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
