package com.rfbsoft.natives.AppSound;

import java.io.File;

public class Main {

    public native int topla(int a , int b);
    public native int carp(int a , int b);

    public static void main(String[] args) {
        File file = new File(".");
        System.load(file.getAbsolutePath() + "/native/Main/jni/build/main.dll");
        Main sound = new Main();
        System.out.println(String.format("Toplam %d Çarpım %d",sound.topla(20,20),sound.carp(20,4)));
    }
}
