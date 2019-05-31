package com.byrfb.araclar;

import com.byrfb.Main;

import processing.core.PVector;

public class Cubuk {
	/*
	 * Her bir kolun 2 tane bölümü o bölümler bunda oluşur
	 */

	PVector basKoordinat = new PVector();

	PVector sonKoordinat = new PVector();

	float uzunluk = 0;

	float angle = 0;

	public Cubuk(PVector basKoordinat, float uzunluk, float angle) {
		super();

		this.basKoordinat = basKoordinat;
		setAngle(angle);
		this.uzunluk = uzunluk;
		setSonKoordinat(basKoordinat, angle);

	}

	/*
	 * Baş koordinat ve açısı verilenin otomatik uzunlukla birlikte son noktası
	 * tespit edilir
	 */
	void setSonKoordinat(PVector basKoordinat, float angle) {
		float sonX = (float) (basKoordinat.x + (Math.cos(Math.toRadians(angle)) * uzunluk));
		float sonY = (float) (basKoordinat.y + (Math.sin(Math.toRadians(angle)) * uzunluk));
		sonKoordinat.set(sonX, sonY);

	}

	public void update() {
		// angleArttir();

		setSonKoordinat(basKoordinat, angle);
	}

	public void draw(float angle) {
		setAngle(angle);
		draw();

	}

	public void draw() {
		this.update();
		Main.sketch.strokeWeight(5);
		Main.sketch.line(basKoordinat.x, basKoordinat.y, sonKoordinat.x, sonKoordinat.y);
		Main.sketch.ellipse(basKoordinat.x, basKoordinat.y, 10, 10);

	}

	public void angleArttir() {
		this.angle += 1;
	}

	public PVector getSonKoordinat() {
		return sonKoordinat;
	}

	public void setSonKoordinat(PVector sonKoordinat) {
		this.sonKoordinat = sonKoordinat;
	}

	/*
	 * Verilen diğer çubukla arasındaki açı değerini döner
	 */
	public float getAngle(Cubuk cubuk) {
		return PVector.angleBetween(this.nor(), cubuk.nor());
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float d) {
		d = 360 - d;
		this.angle = d;

	}

	public void setAngle(Cubuk cubuk, float angle) {
		this.angle = cubuk.getAngle() + angle;
	}

	/*
	 * Merkez koordinat sistemine taşınmış şekilde vektörünü geri döndürür
	 */
	public PVector getVector() {
		float deltaX = sonKoordinat.x - basKoordinat.x;
		float deltaY = sonKoordinat.y - basKoordinat.y;
		return new PVector(deltaX, deltaY);
	}

	/*
	 * Normalize edilmiş Hali döndürürlür
	 */
	public PVector nor() {
		PVector vector = getVector();
		return vector.normalize();
	}

	public PVector getBasKoordinat() {
		return basKoordinat;
	}

	public void setBasKoordinat(PVector basKoordinat) {
		this.basKoordinat = basKoordinat;
	}

	public float getUzunluk() {
		return uzunluk;
	}

	public void setUzunluk(float uzunluk) {
		this.uzunluk = uzunluk;
	}

}
