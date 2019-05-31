package application;

import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {
	Canvas canvas;
	
	public Canvas getCanvas() {
		return canvas;
	}

	public Panel() {
		canvas = new Canvas() {
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g);
				g.fillRect(20, 20, 20, 20);
			}
		};
		
		canvas.setSize(800, 600);

		this.add(canvas);
		this.setSize(800, 600);
	}

}
