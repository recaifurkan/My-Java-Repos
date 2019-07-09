package com.byrfb.guis;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.badlogic.gdx.Gdx;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

public class ExitFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPanel panel;
	private JButton button;
	private JButton button_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExitFrame frame = new ExitFrame();
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
	public ExitFrame() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		
		this.setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(350, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(49, 13, 210, 105);
		textField.setText("\u00C7\u0131kmak \u0130stedi\u011Fine Emin Misin ?");
		textField.setColumns(10);
//		textField.setAlignmentX(CENTER_ALIGNMENT);
		textField.setHorizontalAlignment(JTextField.CENTER);
		contentPane.add(textField);
		
		panel = new JPanel();
		panel.setBounds(103, 150, 100, 25);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gdx.app.exit();
			}
		});
		panel.add(button);
		
		button_1 = new JButton("No");
		panel.add(button_1);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
