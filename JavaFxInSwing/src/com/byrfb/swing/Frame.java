/*
 * By Rfb bu s�n�f ekran da a��lan penceri olu�turur
 * bu sayede ekrana bi tane java fx penceresi bi tane swing panel ekler
 * swing panelde bizim open gl i oynat�ypruz
 * 
 * 
 */

package com.byrfb.swing;

import com.byrfb.javaFx.FxPanel;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
	public final static int WIDTH = 640;
	public final static int HEIGHT = 480;
	public static Container CONTAINER = null;
	SwingPanel swingPanel;
	FxPanel fxPanel;

	public Frame() {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setupFrame();

			}
		});

		createComponent();
		componentleriAyarla();

	}

	/**
	 * altaki metod ekranda buluncak komponentleri frame ekler sonra komponentlerin
	 * boyutlar�n�n ayarlay�p her komponentin kendi i�imi yapmas� i�in thread a�ar
	 * 
	 * 
	 */

	private void componentleriAyarla() {

//		cont.setLayout(new BorderLayout());
//		cont.setLayout(new FlowLayout());
		CONTAINER.setLayout(new GridLayout(0, 2));

//		fxPanel.setLocation(0, 0);
//		swingPanel.setLocation(cont.getWidth()/2, cont.getHeight());

		CONTAINER.add(swingPanel);
		CONTAINER.add(fxPanel);

		swingPanel.setSize(CONTAINER.getWidth() / 2, CONTAINER.getHeight() / 2);
		fxPanel.setSize(CONTAINER.getWidth() / 2, CONTAINER.getHeight());

//		boyutTest();

		new Thread(new Runnable() {

			@Override
			public void run() {
				swingPanel.init();

			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				fxPanel.init();
				fxPanel.setLocation(CONTAINER.getWidth() / 2, 0);

			}
		}).start();

	}

	/**
	 * a��a��daki metot ise swinge eklenen komponentlerin boyutlar�n� kontrol etmek
	 * i�in yaz�ld�
	 */

	private void boyutTest() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println("swing " + swingPanel.getWidth());
					System.out.println("--------");
					System.out.println(fxPanel.getWidth());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();

	}

	/**
	 * a�a��da metot komponentlerin instancelerini al�r
	 */

	private void createComponent() {

		swingPanel = new SwingPanel();

		fxPanel = new FxPanel();

	}

	/**
	 * bu metot frame i ol�tururp ayarlaek�n� d�zenler
	 */
	private void setupFrame() {

		CONTAINER = this.getContentPane();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setUndecorated(true);
		pack();
		setVisible(true);
		setSize(WIDTH, HEIGHT);

	}

}
