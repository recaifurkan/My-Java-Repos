package com.byrfb.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.actions.AddAction;
import com.byrfb.geometry.cartesian.Arc;
import com.byrfb.geometry.cartesian.Line;
import com.byrfb.geometry.cartesian.Point;
import com.byrfb.geometry.utils.MathLib;

import java.util.*;

public class GeometryDrawApplication implements ApplicationListener,InputProcessor
{

	SpriteBatch batch;
	BitmapFont font;
	ShapeRenderer renderer;

	/*
	 * 
	 * points ise ekranda çizilecek olan noktalarý belirlemek için bulunmkata
	 */
	List<Point> points;
	
	/*
	 * 
	 * tmpPoints dairenin çiziminde noktalarýn depolanmasý için kullanýlýyor
	 */
	List<Point> tmpPoints = new ArrayList<Point>();



	@Override
	public void create()
	{
		points = Line.line(new Point(20, 20), new Point(100, 100));

		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		font = new BitmapFont();
		renderer = new ShapeRenderer();
//		points = Arc.arc(new Point(100, 100),
//							 new Point(150, 150),
//							 200,
//							 true);
//		points = Arc.arc(new Point(250, 250), 25, startDegree, cizimDegree);
		
	}

	Vector3 mouse = new Vector3();
	float startDegree = 0;
	float cizimDegree = 75;
	
	
	@Override
	public void render()
	{        
		
		
		
		System.out.println("Radius = " + cizimDegree);
//		cizimDegree ++;
//		cizimDegree =  cizimDegree % 360;
//		points = (Arc.arc(new Point(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()), 250, startDegree, cizimDegree));
	    Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, "fps " + Gdx.graphics.getFramesPerSecond() , 0, Gdx.graphics.getHeight());


		batch.end();
		renderer.begin(ShapeType.Line);

		renderer.setColor(Color.BLACK);
		
		if(sayac ==1) {
			
			// ctrl basýlýyken 1 kere týklandýysa hayali dairenin çizimi için burasý bulunmakta
			// hayali daire ve darinenin kesim noktasý görünmekte
			renderer.setColor(Color.RED);
			
			double radius = MathLib.distance(tmpPoints.get(0), points.get(points.size()-1));
			renderer.circle(tmpPoints.get(0).x(), tmpPoints.get(0).y(), (float) radius);
			renderer.circle(tmpPoints.get(0).x(), tmpPoints.get(0).y(), (float) 5);
			renderer.line(tmpPoints.get(0).getCoord(), mouse.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0));
		}
		
		renderer.setColor(Color.RED);
		
		if(points.size()-1 > 0  )
		renderer.circle(points.get(points.size()-1).x(), points.get(points.size()-1).y(), 15);
		
		
		for(long i = 1 ; i < points.size() ; i++) {
			Point first = points.get((int) (i-1));
			Point second = points.get((int) i);
//			renderer.line(first.getCoord(), second.getCoord());
			renderer.circle(second.x(), second.y(), second.z()   );
			
//			renderer.circle(second.x(), second.y(), 1);
			renderer.setColor(Color.BLACK);
			
		}
		
//		for (Point p : points)
//		{
//			renderer.setColor(Color.BLACK);
//			renderer.circle(p.x(), p.y(), 2);
//		}
		
//		renderer.arc(250, 250, 25, startDegree, cizimDegree);
		renderer.end();
		System.out.println("point size " + points.size());
		update();
	}

	private void update() {
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			cizimDegree += 5;
			
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			startDegree += 20;
			
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			cizimDegree -= 5;
			
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			startDegree -= 20;
			
		}
		
		
	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
	
	boolean isCtrlTyped = false;
	boolean isAltTyped = false;
	int sayac = 0;

	@Override
	/*
	 * burada sayac kontrol tuþuna basýldýðýnda çizilecek olan dairenþn nasýl olacaðýný belirlemek için
	 * kullanýlmasý için var
	 * kontrol tuþu zaten dairenin görsel görünümü alt tuþu ise saat yönünfe mi yoksa teri yönde mi çizilecek onun seçilmesi içn 
	 * kullanýlýyor.
	 * 
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyDown(int)
	 */
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.CONTROL_LEFT)isCtrlTyped = true;
		if(keycode == Input.Keys.ALT_LEFT)isAltTyped = true;
		if(keycode == Input.Keys.C)
			{
			points.clear();
			sayac =0;
			}
		
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.CONTROL_LEFT)isCtrlTyped = false;
		if(keycode == Input.Keys.ALT_LEFT)isAltTyped = false;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(!isCtrlTyped) {
			sayac = 0;
			Point firstPoint;
			
			if(points.size() == 0) {
				firstPoint = new Point(screenX,Gdx.graphics.getHeight() - screenY);
			}
			else {
				firstPoint = points.get(points.size()-1);
			}
				 
			points.addAll(Line.line(firstPoint,new Point(screenX,Gdx.graphics.getHeight() - screenY , cizimDegree < 0 ? 0: cizimDegree)));

			
		}
			// eðer ctrl basýlý deðilse normal çizgi çizer
		
		else {
			// ctrl basýlýysa tmp eklenir bu þelilde daire çizimi için noktalar belirlenmiþ olur
			
			tmpPoints.add(sayac ,new Point(screenX, Gdx.graphics.getHeight() - screenY,cizimDegree<0 ? 0 : cizimDegree ));
			sayac++;
			
			
		}
		
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(sayac == 2) {
			/*
			 * 
			 * alttaki ekleme iþlemi arctaki tüm noktalarýn hesaplanýp eklenmisini yapýyor
			 * 
			 */
			
			float degree = (float) MathLib.calculateAngle(	points.get(points.size()-1).x(), points.get(points.size()-1).y(),
					tmpPoints.get(0).x(), tmpPoints.get(0).y(), tmpPoints.get(1).x(), tmpPoints.get(1).y());
			if(isAltTyped) {
				degree = -degree;
			}
			
			
//			points.addAll(Arc.arc(points.get(points.size()-1),
//				 tmpPoints.get(1),
//				 isAltTyped,
//				 distance));
			points.addAll(Arc.arc(points.get(points.size()-1), tmpPoints.get(0), tmpPoints.get(1), isAltTyped));
			sayac = 0;
			tmpPoints.clear();
		}
		return false;
	}

	

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}

/*
 * ark çzimiyle ilgili 2 farklý metot bulunmakta birisi
 * radiusu ile çizer
 * ötekisi ise baþlangýç bitiþ bi de merkez noktasýyla çizer
 * 
 * 
 */
