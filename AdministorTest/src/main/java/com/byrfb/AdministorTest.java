package com.byrfb;

import com.byrfb.administor.SUDO;
import com.byrfb.administor.SuperUserApplication;


import java.io.File;


/*
Bu örneğinle çok kolay bir şekilde java programı administor olarak çalıştırabilmektesin
main metoda direk bizim Su.run() gönderen bundan sonra extend edilen sınıftaki runAdministor metodu başlarsa program
administor demektir
eğer notAdministor başlarsa administor olarak başlatılmazsa yapılacaklar belitilecek
Yalnız programı artifact ederken superuser kğütüphanesi ile programının aynuı jar içerisinde olmasına dikkat etmelisin
zaten direk source i de gradle olrak içine ekledin

Bu projede normalde ulaşılamayacakmolan başlangıç klasörğnde bi tane dosya oluşturulmaya çalışıldı ve başarılı  bir
şekilde oluşturuldu


yalnız development yaparken
jdk da kş java yı yönetici olarak çalıştırı true yapman lazım
sonra ideyim de yönetici olrak çalıştırırsan çok iyi bir şekilde development yaparsın

artifaxt yaparken de zaten normal bir işlem yapabilirsin
launch 4 j ile yapacaksan yönetici olraka çalıştırman gerekebilir
onda da bir tane .manifest dosyası oluşturuyorsun ve o şekilde çalıştırabiliyorsun




 */
public class AdministorTest extends SuperUserApplication {

    public static void main(String[] args) {

        SUDO.run(new AdministorTest(), args);

    }

    public int runAdministor(String[] args) {

        String homePath = System.getProperty("user.home");
        String startUpPath = homePath + "//" + "AppData//Roaming//Microsoft//Windows//Start Menu//Programs//Startup";
        String filePath = startUpPath + "//denemdsdsadade.txt";
        System.err.println(filePath);


        try {

            File file = new File(filePath);
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;

    }

    @Override
    public int runNotAdministor() {
        System.out.println("Not administor");
        return 0;
    }
}