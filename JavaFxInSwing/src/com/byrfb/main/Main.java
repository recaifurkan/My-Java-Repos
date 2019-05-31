
/*
 * karde� bu class program�n ba�lang�c� biz bu programda java fx ile singi birle�tirip swing i�inde a��lan lwjgl kullan�p ekranda open gl ile 
 * grafiksel g�sterim yapmay� test ettik �ok da g�zel �al��makta
 * buna i�te sonra lwjgl 3 entegrasyonuada yap�labilir o y�zden dikkat et
 * 
 * 
 * 
 * 
 */

package com.byrfb.main;

import com.byrfb.swing.Frame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

	public static void main(String[] args) {
		
		
		Frame frame = new Frame();
		/*
		 * maalesef daha key listener ile programdan tu�a bas�ld� bilgisi almay�
		 * ba�aramad�k
		 */
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("basildi");

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		System.out.println("bitti");
		

	}

}
