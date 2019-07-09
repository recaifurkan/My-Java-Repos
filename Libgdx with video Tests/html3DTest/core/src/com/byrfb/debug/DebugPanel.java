package com.byrfb.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bulletphysics.test.bullet3dcontacttests.BulletTest;

public class DebugPanel {
	
	String getText() {
		return 
		addLine("Fps : " + Gdx.graphics.getFramesPerSecond())+
//		addLine("Delta Time : " + Gdx.graphics.getDeltaTime())+
//		addLine("Frame I : " + Gdx.graphics.getFrameId())+
//		addLine("Native Heap : " + Gdx.app.getNativeHeap()) + 
		addLine("App Type : " + Gdx.app.getType())
//		addLine("Instances size : " + bulletTest.getInstances().size) +
//				addLine("World Object size : " + bulletTest.getDynamicsWorld().getCollisionObjectArray().size())+
//		addLine("World Object size : " + bulletTest.getDynamicsWorld().getNumCollisionObjects())+
//		addLine("Actions num : " + bulletTest.getDynamicsWorld().getNumActions()) +
//				addLine("Actions num : " + bulletTest.getDynamicsWorld())+
//		addLine("Debug Mode  : " + DebugModesType.getName(bulletTest.getDebugDrawer().getDebugMode()))	


		;
	}

	SpriteBatch spriteBatch;
	BitmapFont font;
	private BulletTest bulletTest;
	

	public DebugPanel() {
		this(null);
	}

	public DebugPanel(BulletTest bulletTest) {
		this.bulletTest = bulletTest;
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();

	}

	public void render() {
		
		String text = getText();
		spriteBatch.begin();
		font.draw(spriteBatch, text, 0, Gdx.graphics.getHeight());
		spriteBatch.end();
	}



	String addLine(String text) {
		return text + "\n";

	}

	public void resize(int width, int height) {

		spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);

	}

}
