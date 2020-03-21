package com.byrfb.objects;


public enum KontenjanBolum {
    Çocuk("Pedodonti"),
    Radyo("Radyoloji"),
    Orto("Ortıdınti"),
    Perio("Periodontoloji"),
    Cerrahi("Cerrahi"),
    Endo("Endodonti"),
    Resto("Restoratif"),
    Prote("Protez"),
    Bolumsuz("Herhangi bir bölğm olmasın");

    String text;

    KontenjanBolum(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }


    public String getName() {
        return this.name();

    }

}