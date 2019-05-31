package com.byrfb.utils;

import com.byrfb.Main;
import com.byrfb.Skecth;
import com.byrfb.araclar.Cubuk;
import com.byrfb.araclar.TwoCubukSystem;

import processing.core.PVector;

public class Selector {

	PVector coord;

	public Selector() {
		this.coord = new PVector();
	}

	public void draw() {
		getSkecth().ellipseMode(getSkecth().CENTER);
		getSkecth().ellipse(coord.x, coord.y, 20, 20);
	}

	Skecth getSkecth() {
		return Main.sketch;
	}

	public PVector getCoord() {
		return coord;
	}

	public void setCoord(float x, float y) {
		this.coord.set(x, y);
	}

	public double getAngle(Cubuk cubuk) {
		float deltaX = coord.x - cubuk.getBasKoordinat().x;
		float deltaY = coord.y - cubuk.getBasKoordinat().y;
		double aci = Math.toDegrees(Math.atan2(deltaY, deltaX));
		if (aci < 0) {
			return -aci;
		}
		return 360 - aci;
	}

	public double getAngle(TwoCubukSystem sistem) {
		Cubuk cubuk = sistem.getCubuks()[0];
		return getAngle(cubuk);
	}

	public float getDistance(Cubuk cubuk) {
		return PVector.dist(coord, cubuk.getBasKoordinat());
	}

	public void setCoord(PVector vectkonum) {
		this.setCoord(vectkonum.x,vectkonum.y);
		
	}

}
