package com.byrfb.objects;

public class Brans {

    String universite;
    String fakulte;
    String bolum;

    public Brans(String programAdi) {
        String programs[] = programAdi.split("/");
        this.universite = programs[0];
        this.fakulte = programs[1];
        this.bolum = programs[2];
    }


    public String getUniversite() {
        return universite;
    }

    public void setUniversite(String universite) {
        this.universite = universite;
    }

    public String getFakulte() {
        return fakulte;
    }

    public void setFakulte(String fakulte) {
        this.fakulte = fakulte;
    }

    public String getBolum() {
        return bolum;
    }

    public void setBolum(String bolum) {
        this.bolum = bolum;
    }


    @Override
    public String toString() {
        return "Brans{" +
                "universite='" + universite + '\'' +
                ", fakulte='" + fakulte + '\'' +
                ", bolum='" + bolum + '\'' +
                '}';
    }


}
