package com.byrfb.parsers;


import com.byrfb.objects.Brans;
import com.byrfb.objects.Headers;
import com.byrfb.objects.Kontenjan;
import com.byrfb.program.Utils;
import org.apache.poi.ss.usermodel.Row;

import static com.byrfb.program.Main.kontenjans;

public class RowParser implements Parser<Row> {

    @Override
    public void parse(Row parseable) {

        Kontenjan kont = new Kontenjan();
        kont.setProgramKod(parseable.getCell(Headers.Program_Kodu.getIndex()).getStringCellValue());
        String brasString = parseable.getCell(Headers.Program_Adi.getIndex()).getStringCellValue();
        Brans brans = new Brans(brasString);
        kont.setBolum(brans);
        int index = 0;
        try {
            kont.setPuanTuru(parseable.getCell(Headers.Puan_Turu.getIndex()).getStringCellValue());
        } catch (Exception e) {
            index = 1;
        }
        kont.setPuanTuru(parseable.getCell(index + Headers.Puan_Turu.getIndex()).getStringCellValue());
        kont.setGenelKont(parseable.getCell(index + Headers.Genel_Kont.getIndex()).getStringCellValue());
        kont.setGenelYerlestirme(parseable.getCell(index + Headers.Genel_Yeri.getIndex()).getStringCellValue());
        String enKucukPuanString = parseable.getCell(index + Headers.En_Kucuk_Puan.getIndex()).getStringCellValue();
        String ennBuyukPuanString = parseable.getCell(index + Headers.En_Buyuk_Puan.getIndex()).getStringCellValue();
        String obKontString = parseable.getCell(index + Headers.OB_Kont.getIndex()).getStringCellValue();
        String obYerString = parseable.getCell(index + Headers.OB_Yer.getIndex()).getStringCellValue();
        String obkEnKucukPuanString = parseable.getCell(index + Headers.OBK_En_Kucuk_Puan.getIndex()).getStringCellValue();
        String obkEnBuyukPuanString = parseable.getCell(index + Headers.OBKEn_Buyuk_Puan.getIndex()).getStringCellValue();

        kont.setEnKucukPuan(Utils.StringToFloat(enKucukPuanString));
        kont.setEnBuyukPuan(Utils.StringToFloat(ennBuyukPuanString));
        kont.setObKont(Utils.StringToFloat(obKontString));
        kont.setObYer(Utils.StringToFloat(obYerString));
        kont.setObkEnKucukPuan(Utils.StringToFloat(obkEnKucukPuanString));
        kont.setObkEnBuyukPuan(Utils.StringToFloat(obkEnBuyukPuanString));
        kontenjans.add(kont);
    }


}

