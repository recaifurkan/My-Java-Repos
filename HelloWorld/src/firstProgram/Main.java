package firstProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("IlkProgram");
		frame.setVisible(true);
		frame.setSize(300,400);
		JButton buton = new JButton("Buton");
		frame.add(buton);
		buton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Recai");
				frame.setTitle("Recai");
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
