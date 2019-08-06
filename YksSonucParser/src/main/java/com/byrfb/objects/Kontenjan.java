package com.byrfb.objects;


public class Kontenjan implements Comparable<Kontenjan>{
    String programKod;
    Brans bolum;
    String puanTuru;
    String genelKont;
    String genelYerlestirme;
    float enKucukPuan;
    float enBuyukPuan;
    float obKont;
    float obYer;
    float obkEnKucukPuan;

    @Override
    public String toString() {
        return "Kontenjan{" +
                "programKod='" + programKod + '\'' +
                ", bolum=" + bolum +
                ", puanTuru='" + puanTuru + '\'' +
                ", genelKont='" + genelKont + '\'' +
                ", genelYerlestirme='" + genelYerlestirme + '\'' +
                ", enKucukPuan=" + enKucukPuan +
                ", enBuyukPuan=" + enBuyukPuan +
                ", obKont=" + obKont +
                ", obYer=" + obYer +
                ", obkEnKucukPuan=" + obkEnKucukPuan +
                ", obkEnBuyukPuan=" + obkEnBuyukPuan +
                '}';
    }

    float obkEnBuyukPuan;


    public String getProgramKod() {
        return programKod;
    }

    public void setProgramKod(String programKod) {
        this.programKod = programKod;
    }

    public Brans getBolum() {
        return bolum;
    }

    public void setBolum(Brans bolum) {
        this.bolum = bolum;
    }

    public String getPuanTuru() {
        return puanTuru;
    }

    public void setPuanTuru(String puanTuru) {
        this.puanTuru = puanTuru;
    }

    public String getGenelKont() {
        return genelKont;
    }

    public void setGenelKont(String genelKont) {
        this.genelKont = genelKont;
    }

    public String getGenelYerlestirme() {
        return genelYerlestirme;
    }

    public void setGenelYerlestirme(String genelYerlestirme) {
        this.genelYerlestirme = genelYerlestirme;
    }

    public float getEnKucukPuan() {
        return enKucukPuan;
    }

    public void setEnKucukPuan(float enKucukPuan) {
        this.enKucukPuan = enKucukPuan;
    }

    public float getEnBuyukPuan() {
        return enBuyukPuan;
    }

    public void setEnBuyukPuan(float enBuyukPuan) {
        this.enBuyukPuan = enBuyukPuan;
    }

    public float getObKont() {
        return obKont;
    }

    public void setObKont(float obKont) {
        this.obKont = obKont;
    }

    public float getObYer() {
        return obYer;
    }

    public void setObYer(float obYer) {
        this.obYer = obYer;
    }

    public float getObkEnKucukPuan() {
        return obkEnKucukPuan;
    }

    public void setObkEnKucukPuan(float obkEnKucukPuan) {
        this.obkEnKucukPuan = obkEnKucukPuan;
    }

    public float getObkEnBuyukPuan() {
        return obkEnBuyukPuan;
    }

    public void setObkEnBuyukPuan(float obkEnBuyukPuan) {
        this.obkEnBuyukPuan = obkEnBuyukPuan;
    }


    public static int sortIndex = 2;
    @Override
    public int compareTo(Kontenjan o) {
        switch (sortIndex) {
            case 0:

                return (o.getBolum().getUniversite().compareToIgnoreCase(this.getBolum().getUniversite()));

            case 1:

                return Float.compare(o.getEnBuyukPuan(), this.getEnBuyukPuan());

            case 2:

                return (o.getBolum().getBolum().compareToIgnoreCase(this.getBolum().getBolum()));


            default:
                return Float.compare(o.getEnKucukPuan(), this.getEnKucukPuan());

        }
        // TODO Auto-generated method stub

    }

}
