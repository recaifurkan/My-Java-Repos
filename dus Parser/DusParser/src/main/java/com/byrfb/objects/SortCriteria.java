package com.byrfb.objects;

public enum SortCriteria {
    universite("Üniversite adına göre sıralansın"),
    yuksekPuan("Alınan en yüksek puana göre sılanasın"),
    bransIsim("Branş ismine göre sıoıoralansın"),
    kucukPuan("Alınan en küçük puana göre sıralanasın");

    String text;

    SortCriteria(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}