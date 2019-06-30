package com.byrfb.robot;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import com.byrfb.kinematic.Link;
import com.byrfb.main.Robot;

public class Robot3d implements ApplicationListener {
	SpriteBatch batch;
	ShapeRenderer renderer;
	Robot robot;
	Camera cam;
	FirstPersonCameraController inputController;
	
	BitmapFont font;
	CharSequence text = "" + "Arttýrma iþlemleri için farklý tuþlara bakmanýz gerekmekte \n"
			+ "1 tuþu açý arttýrm azaltma \n" + "2 tuþu da bi açý olmasý lazým \n"
			+ "3 ve 4 tuþlarý uzunluklarý deðiþtirmeye hyarar \n"
			+ "+ tuþuna basarak arttýtma mý azalma mý yaoýlcak karar verirlir \n"
			+ "sað tuþa basarak linki deðiþtirebilirsiniz";
	
	@Override
	public void create() {
		renderer = new ShapeRenderer();
		batch = new SpriteBatch();
		robot = new Robot();

		cam = new PerspectiveCamera(72, 800, 800);
		font = new BitmapFont(Gdx.files.internal("font/arial-15.fnt"), Gdx.files.internal("font/arial-15.png"), false);
		inputController = new FirstPersonCameraController(cam);
		Gdx.input.setInputProcessor(inputController);
		Link link0 = robot.getTable().getLinks().get(0);
		cam.combined.translate(-10, -68, -32);
		cam.lookAt(link0.getCoord().x, link0.getCoord().y, link0.getCoord().z);

	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void render() {
		
		 Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);      //clears the buffer 
		update();
		
		renderer.setProjectionMatrix(cam.combined);
		renderer.begin(ShapeType.Line);
		
		
		for(int i = 1 ; i < robot.getTable().getLinks().size() ; i ++) {
			
			Link link0 = robot.getTable().getLinks().get(i-1);
			Link link1 = robot.getTable().getLinks().get(i);
			
//			System.out.println(link1.getCoord());
			
			renderer.setColor(Color.RED);
			renderer.point(link0.getCoord().x,
					link0.getCoord().y,
					link0.getCoord().z/20);
			renderer.setColor(Color.WHITE);
			
			renderer.line(
					link0.getCoord().x,
					link0.getCoord().y,
					link0.getCoord().z/20,
					link1.getCoord().x,
					link1.getCoord().y,
					link1.getCoord().z/20);
		}
		
		
		
		renderer.end();
		
	    batch.begin();

	    font.draw(batch, text, 0, Gdx.graphics.getHeight() - 10);

	    batch.end();
	  
		
	}
	
	int index = 1;
	boolean arttir = false;
	int arttirmaVel = 5;

	private void update() {
		/*
		 * burda + tuþuna basýnca azalma mý yapýlcak artma mý yapýlcak seçilmesi
		 * yapýloýyor 1 tuþu joint angle deðiþtirir diðerleri zaten yazýyopr ne olsuðu
		 * 
		 * 
		 * 
		 * 
		 */

		if (Gdx.input.isKeyJustPressed(Input.Keys.PLUS)) {
			arttir = !arttir;
		}

		if (arttir) {
			arttirmaVel = 5;
		} else {
			arttirmaVel = -5;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1)) {

			Link link = robot.getTable().getLinks().get(index);
			link.setJointAngle((float) (link.getJointAngle() + arttirmaVel));

//			
		}

		if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)) {

			Link link = robot.getTable().getLinks().get(index);
			link.setLinkTwist((float) (link.getLinkTwist() + arttirmaVel));

//			
		}

		if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)) {

			Link link = robot.getTable().getLinks().get(index);
			link.setLinkOffset((float) (link.getLinkOffset() + arttirmaVel));

//			
		}

		if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)) {

			Link link = robot.getTable().getLinks().get(index);
			link.setLinkLenght((float) (link.getLinkLenght() + arttirmaVel));

//			
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			index++;
			if (index > 5) {
				index = 1;
			}

//		
		}

		System.out.println(index);

		robot.update();
		inputController.update();
//		System.out.println(cam.combined.getTranslation(new Vector3()));

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
