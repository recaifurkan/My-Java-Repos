package com.byrfb.swing;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.byrfb.lwjgl.DisplayManager;
import com.byrfb.lwjgl.Game;
import com.mygdx.game.GdxDemo3D;

import javax.swing.*;
import java.awt.*;

public class SwingPanel extends JPanel {

	/**
	 * bu sýnýf içinde open gl açacak olan canvasý parýndýrýr
	 */

	public SwingPanel() {

	}

	public void init() {

		this.setBackground(Color.BLACK);
		System.out.println("swingpanel");
//		Canvas canvas = createAwtCanvas();
		LwjglAWTCanvas canvas = createLibgdxCanvas();

		/**
		 * display manager ile open gl için gerekli olan ayarlarýn yapýlabilmesi için
		 * class oluþturuyor biliyorsun ki open gl render edebilmek için canvas
		 * ihtiyacýn var ve bir tane game interfacesini implemente eden classa bununla
		 * birlikte render create dispose update metotlarý rahatça çaðrlabiliyor
		 */

	}

	/*
	 * 
	 * direk awt canvas açabilirsin
	 */
	private Canvas createAwtCanvas() {
		Canvas canvas = new Canvas();
		canvas.setSize(this.getWidth(), this.getHeight());
		this.add(canvas);
		DisplayManager manager = new DisplayManager(canvas, new Game());
		return canvas;
	}

	/*
	 * 
	 * libgdx canvasý direk sing içinde açmana sý saðlar
	 */

	private LwjglAWTCanvas createLibgdxCanvas() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = this.getHeight();
		config.width = this.getWidth();
		LwjglAWTCanvas canvas = new LwjglAWTCanvas(new GdxDemo3D());
		canvas.getCanvas().setSize(this.getWidth() * 2, this.getHeight() * 2);
		this.add(canvas.getCanvas());
		return canvas;
	}

}
