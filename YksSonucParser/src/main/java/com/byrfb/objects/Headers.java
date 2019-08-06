package com.byrfb.objects;

/**
 * Program Kodu
 * Program Adı
 * Puan Türü
 * Genel Kont.
 * Genel Yerl.
 * En Küçük Puan
 * En Büyük Puan
 * OB Kont.
 * OB Yer.
 * OBK En Küçük Puan
 * OBK En Büyük Puan
 */

public enum Headers {
    Program_Kodu(0),
    Program_Adi(1),
    Puan_Turu(2),
    Genel_Kont(3),
    Genel_Yeri(4),
    En_Kucuk_Puan(5),
    En_Buyuk_Puan(6),
    OB_Kont(7),
    OB_Yer(8),
    OBK_En_Kucuk_Puan(9),
    OBKEn_Buyuk_Puan(10);

    int columnIndex;
    Headers(int index){
        this.columnIndex = index;
    }

    public int getIndex(){
        return this.columnIndex;
    }

}
