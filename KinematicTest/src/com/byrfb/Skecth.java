package com.byrfb;

import java.awt.event.KeyEvent;

import java.util.ArrayList;

import java.util.List;

import com.byrfb.araclar.Cubuk;
import com.byrfb.araclar.Point;
import com.byrfb.araclar.TwoCubukSystem;
import com.byrfb.utils.Selector;

import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PVector;
/*
Ana başlangıç Sket için burası


*/

public class Skecth extends PApplet {

	ArrayList<Cubuk> cubuks;

	/*
	 * Gui için kütüphane
	 */
	ControlP5 cp5;
	
	ArrayList<float[]> konums = new ArrayList<float[]>(); 
	
	
	

	/*
	 * Alttakiler sağ ve sol olmak üzre iki tane sistem kollar
	 */
	TwoCubukSystem sistem;
	TwoCubukSystem sistem1;

	/*
	 * Bu alttaki de sağ tıklama yaptığında ne kadar hızla dönme yapmasının
	 * değişkeni
	 */
	int radyalHiz;
	int yariCap;

	/*
	 * Mousenin takibi ve seçilecek yerin belirtilmesi için olan şey
	 */
	Selector selector;

	public void settings() {

		size(1500, 800);

	}

	public void setup() {
		Main.setSkecth(this);
		sistem = new TwoCubukSystem(new PVector(width / 2 + 50, height - 10));
		sistem1 = new TwoCubukSystem(new PVector(width / 2 - 50, height - 10));
		sistem.setTaraf(true);

		setUpGui();
		selector = new Selector();

	}

	int index = 0;
	public void draw() {
		update();
		background(255);
		sistem.draw();
		sistem1.draw();
		sistem.printAngle(selector);
		sistem1.printAngle(selector);
		selector.draw();
		for (Point path : paths) {
			path.draw();
		}
		// if(paths.size() > 1000) {
		// paths.clear();
		// }
		textSize(20);
		fill(0);
		text(frameRate + " fps", 10, 100);
		text(paths.size() + " adet", 10, 120);
		
		PVector selectorCoord = selector.getCoord();
		text( selectorCoord.toString(), selectorCoord.x - 20, selectorCoord.y -20);
		
		
		
	}
	
	long millis;
	long tmpMillis;
	

	 private void saniyedeBir() {
		
//		PVector vec = new PVector(konum[0], konum[1], konum[2]);
//		selector.setCoord(vec.x, vec.y);
//		index++;
		
			

	 }

	ArrayList<Point> paths = new ArrayList<>();
	float aci = 0;

	
	boolean isPathFinish = false;
	private void update() {

		mouseDispatcher();

		if(konums.size() > 0) {
			float[] konum = konums.get(index);
			findLine(selector.getCoord().x, selector.getCoord().y, konum[0], konum[1]);
		}
		if(isPathFinish) {
			index++;
			isPathFinish = false;
		}
		if (index >= konums.size() )
			index = 0;
		
		

		if (millis - tmpMillis > 1000) {
			saniyedeBir();
			tmpMillis = millis;
		}

		millis = millis();
	}

	/*

	 */
	private void mouseDispatcher() {
		PVector vect = new PVector(mouseX, mouseY);
		
//		selector.setCoord(vect.x, vect.y);
		if (mousePressed) {

			if (mouseButton == RIGHT) {
				vect = PVector.fromAngle(aci);
				vect.mult(yariCap);
				vect.add(mouseX, mouseY);

				selector.setCoord(vect.x, vect.y);
				aci += 0.01 * radyalHiz;
				paths.add(new Point(vect));

			}

			if (mouseButton == LEFT) {
				System.out.println(selector.getCoord());
				vect = selector.getCoord();
				float[] nowKonum = new float[]{mouseX,mouseY};
				boolean isContain = false;
				for(float[] konum : konums) {
					if(nowKonum[0] == konum[0] && nowKonum[1] == konum[1]) {
						isContain = true;
					}
				}
				if(!isContain) {
					konums.add(nowKonum);
				}
				
				paths.add(new Point(vect.x,vect.y));
			}

		}

	}
	
	

	@Override
	public void keyPressed() {
		/*
		 * ekranın temizlenmedi için
		 */
		if (keyCode == KeyEvent.VK_C) {
			paths.clear();
			konums.clear();
		}

		/*
		 * Ekran fotosu alınması için
		 */
		if (keyCode == KeyEvent.VK_S) {
			save("image.jpeg");
			paths.clear();
		}

		if (keyCode == KeyEvent.VK_R) {
			mouseX = width / 2;
			mouseY = height / 2;
			selector.setCoord(mouseX, mouseY);
		}
		
		if (keyCode == KeyEvent.VK_V) {
			paths.clear();
		}

	}

	private void setUpGui() {
		cp5 = new ControlP5(this);

		createSlider("radyalHiz", 300, new PVector(100, 350));
		createSlider("yariCap", 500, new PVector(200, 350));
		// add a vertical slider

	}

	private void createSlider(String degiskenAdi, int range, PVector position) {

		cp5.addSlider(degiskenAdi).setColorLabel(color(0)).setPosition(position.x, position.y).setSize(20, 100)
				.setRange(1, range).setValue(5);

		cp5.getController(degiskenAdi).setLabel(degiskenAdi);
		cp5.getController(degiskenAdi).getValueLabel().align(ControlP5.LEFT, ControlP5.BOTTOM_OUTSIDE).setPaddingX(0)
				.setColor(0)

		;

	}
	
public void findLine( float x, float y, float konum, float konum2) {
		
		
		
		float dx = Math.abs(konum - x);
		float dy = Math.abs(konum2 - y);
		
		float hiz = radyalHiz;
		
		float sx = x < konum ? hiz : -hiz; 
		float sy = y < konum2 ? hiz : -hiz; 
		
		float err = dx-dy;
		float e2;
		float currentX = x;
		float currentY = y;
		
		
		
		
		// buraya plot ettirecen
		PVector vectCurrent = new PVector(currentX, currentY);
		PVector vectkonum = new PVector(konum, konum2);
		
		
		
		
		if(PVector.dist(vectCurrent, vectkonum)<hiz) {
			selector.setCoord(vectkonum);
			currentX = konum;
			currentY = konum2;
			isPathFinish = true;
		}
		
		e2 = 2*err;
		if(e2 > -1 * dy) {
			err = err - dy;
			currentX = currentX + sx;
		}
		
		if(e2 < dx) {
			err = err + dx;
			currentY = currentY + sy;
		}
		selector.setCoord(currentX, currentY);
				
		
}

}

// public void setup() {
// Cubuk.setSkecth(this);
//// frameRate(2);
//
//// setCubuks();
// setUpGui();
//
// }



// public void draw() {
// background(255);
// for (Cubuk cubuk : cubuks) {
// cubuk.draw();
// }
//
// if (slider != cubuks.size()) {
// setCubuks();
// }
//


//
// void setCubuks() {
// cubuks = new ArrayList<Cubuk>();
// Cubuk lastCubuk = new Cubuk(new PVector(width / 2, height / 2), 20, 20);
// for (int i = 0; i < slider; i++) {
// Cubuk cubuk2 = new Cubuk(lastCubuk.getSonKoordinat(), i * 10, i * 20);
// cubuks.add(cubuk2);
// lastCubuk = cubuk2;
// }
//
// }