package com.byrfb.araclar;

import com.byrfb.Main;

import processing.core.PVector;

/*
Ekrana çizim için yapılmış sınıf
*/
public class Point {
	PVector coord;

	public Point(PVector coord) {
		super();
		this.coord = coord;
	}

	public Point(float x, float y) {
		this.coord = new PVector(x,y);
	}

	public void draw() {
		Main.sketch.ellipse(coord.x, coord.y, 5, 5);
	}

}
