package com.byrfb;

import processing.core.PApplet;
/*
 * Baþlangýç sýnýfý burasý burdan sketc isimli sýnýfa geçilip 
 * ordan nesleneler falan oluþturuluyor iþte babbba
 * burada kosinüs teoremini kullnarak robot kolun uç kýsmýnýn korrdinatnýný hesaplayabiiyorsun
 * 
 * 
 * 
 */

public class Main {

	public static Skecth sketch;
	
	public static void setSkecth(Skecth sc) {
		sketch = sc;
	}
	public static void main(String[] args) {
		PApplet.main(Skecth.class);

	}

}
