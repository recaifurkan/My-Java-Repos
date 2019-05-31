package src.com.byrfb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JLabel vizeBirLabel = new JLabel("Vize 1");
        JTextField vize1 = new JTextField(5);
        JLabel vizeIkiLabel = new JLabel("Vize 2");
        JTextField vize2 = new JTextField(5);
        JButton button = new JButton("HEsapla");
        JLabel hesaplananNot = new JLabel("-------");

        JPanel panel = new JPanel();

        panel.add(vizeBirLabel);
        panel.add(vize1);
        panel.add(vizeIkiLabel);
        panel.add(vize2);
        panel.add(button);
        panel.add(hesaplananNot);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

        vize1.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                vize1.select(0, vize1.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                vize1.select(0, 0);
            }
        });

        vize2.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                vize2.select(0, vize2.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                vize2.select(0, 0);
            }
        });

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                float vizeBir = 0;
                float vizeIki = 0;

                String basilacakDeger = "";
                try {

                    vizeBir = Float.parseFloat(vize1.getText());
                    vizeIki = Float.parseFloat(vize2.getText());

                } catch (Exception exception) {

                    basilacakDeger = "Lütfen Sayı Giriniz";

                }

                float finalNot = finalHesapla(vizeBir, vizeIki);

                if (basilacakDeger.equals(""))
                    basilacakDeger = String.valueOf(finalNot);
                hesaplananNot.setText(basilacakDeger);
                frame.pack();
            }
        });

    }

    static float finalHesapla(float vize1, float vize2) {
        float vizeDegerlendirme = (vize1 * 20 / 100) + (vize2 * 20 / 100);

        float finalNot = (60 - vizeDegerlendirme) * 100 / 60;

        return finalNot;

    }
}