
/*
 * kardeþ bu class programýn baþlangýcý biz bu programda java fx ile singi birleþtirip swing içinde açýlan lwjgl kullanýp ekranda open gl ile 
 * grafiksel gösterim yapmayý test ettik çok da güzel çalýþmakta
 * buna iþte sonra lwjgl 3 entegrasyonuada yapýlabilir o yüzden dikkat et
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
		 * maalesef daha key listener ile programdan tuþa basýldý bilgisi almayý
		 * baþaramadýk
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
