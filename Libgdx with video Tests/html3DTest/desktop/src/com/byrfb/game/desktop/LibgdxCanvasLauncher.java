package com.byrfb.game.desktop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import com.byrfb.bullet3dcontacttests.BulletTest;

import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LibgdxCanvasLauncher extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibgdxCanvasLauncher frame = new LibgdxCanvasLauncher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LibgdxCanvasLauncher() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1295, 714);
		contentPane = new JPanel();
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		final BulletTest test = new BulletTest();
		LwjglCanvas gameCanvas = new LwjglCanvas(test);
		contentPane.setLayout(null);

		Canvas canvas = gameCanvas.getCanvas();
		// canvas= new Canvas();
		canvas.setLocation(10, 10);
		canvas.setSize(527, 636);
		contentPane.add(canvas);

		final JButton btnHover = new JButton("Hover");
		btnHover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < 100; i++) {
					test.spawn();

				}

			}
		});
		btnHover.addMouseListener(new MouseAdapter() {
			Color color;

			@Override
			public void mouseEntered(MouseEvent arg0) {
				color = btnHover.getBackground();
				btnHover.setBackground(Color.BLACK);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnHover.setBackground(color);
			}
		});
		btnHover.setBounds(895, 59, 97, 25);
		contentPane.add(btnHover);

	}
}
