package com.byrfb.araclar;

import com.byrfb.utils.Selector;

import processing.core.PVector;

/*
Bi bakıma kol olarak düşünülebilir
2 tane çubuk birleştirir
ona göre işlemler yapar
*/
public class TwoCubukSystem {

	Cubuk[] cubuks = new Cubuk[2];

	float uzunluk = 400;
	boolean taraf = false;

	public TwoCubukSystem(PVector baslangicPoint) {
		cubuks[0] = new Cubuk(baslangicPoint, uzunluk, 0);
		cubuks[1] = new Cubuk(cubuks[0].getSonKoordinat(), uzunluk, 0);
	}

	public void draw() {
		for (Cubuk cubuk : cubuks) {
			cubuk.draw();
		}
	}

	/*
	 * İçinde bulunan 2 tane çizgi arasındaki açı değerini hesaplar ve döner
	 */
	public double getAngleBetween() {
		return Math.toDegrees(PVector.angleBetween(cubuks[0].nor(), cubuks[1].nor()));
	}

	public Cubuk[] getCubuks() {
		return cubuks;
	}

	public void setCubuks(Cubuk[] cubuks) {
		this.cubuks = cubuks;
	}

	/*
	 * Kolun bitim kısmının koordinatını geri döner
	 */
	public PVector getEndPoint() {
		return cubuks[1].sonKoordinat;
	}

	/*
	 * Kolun omuz kısmını geri döndürür
	 */
	public Cubuk getFirst() {
		return cubuks[0];

	}

	/*
	 * El ksmını geri döndürür
	 */
	public Cubuk getSecong() {
		return cubuks[1];

	}

	/*
	 * Asıl hesalamanın yapıldığı kısım Kolun oluşturduğu üzgene göre hesaplamasını
	 * yapar ve açı değerlerini hesaplar buna göre çim yapılır zaten algoritma
	 * cosinüs teoremine göre heesaplama yapmaktadır
	 */
	public void printAngle(Selector selector) {

		// lenght of sides be a, b, c
		float a = selector.getDistance(getFirst());
		float b = getFirst().getUzunluk();
		float c = getSecong().getUzunluk();

		float a2 = a * a;
		float b2 = b * b;
		float c2 = c * c;

		// From Cosine law
		float alpha = (float) Math.acos((b2 + c2 - a2) / (2 * b * c));
		float betta = (float) Math.acos((a2 + c2 - b2) / (2 * a * c));
		float gamma = (float) Math.acos((a2 + b2 - c2) / (2 * a * b));

		// Converting to degree
		alpha = (float) (alpha * 180 / Math.PI);
		betta = (float) (betta * 180 / Math.PI);
		gamma = (float) (gamma * 180 / Math.PI);

		double aci = selector.getAngle(getFirst());
		// float aci1 = getFirst().getAngle(getSecong());
		// double aci2 = selector.getAngle(getSecong());

		if (!taraf) {
			getFirst().setAngle((float) (aci + betta));
			getSecong().setAngle(getFirst(), 180 - alpha);
		} else {
			getFirst().setAngle((float) (aci - betta));
			getSecong().setAngle(getFirst(), 180 + alpha);
		}

		// System.out.println("1 : " + aci + " 2 : " + aci1 + " 3 : " + aci2 );
		//
		//
		// // printing all the angles
		// System.out.println("alpha : " + alpha + " betta : " + betta + " gamma : " +
		// gamma );

	}

	/*
	 * Hangi taraf olacağı bu meotla belirtilir sonuçta iki kol olcak ve hangisi sağ
	 * hangi sol olduğu burda bildirilmekte
	 * 
	 */
	public void setTaraf(boolean taraf) {
		this.taraf = taraf;
	}

}

// boolean randomTaraf() {
// if(Math.random() < 0.5) {
// return true;
// }
// return false;
//
// }
