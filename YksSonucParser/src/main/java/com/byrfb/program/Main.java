package com.byrfb.program;

import com.byrfb.objects.Kontenjan;
import com.byrfb.parsers.ParserUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    /**
     *
     * Bu programla işte yks de ki üniversite ve böülğmleri puana göre vs vs sıralayabilmek için
     * parse etme işlemi yaptık
     * ayrıca pdf to xcl e çevirme yapan bir tane de program keşfettin
     *
     */

    public static ArrayList<Kontenjan> kontenjans = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        final String TABLO1PATH = "documents/tablo1.xcl";
        final String TABLO2PATH = "documents/tablo2.xcl";
        File input = new File(TABLO2PATH);
        FileInputStream fis = new FileInputStream(input);

        HSSFWorkbook workBook = new HSSFWorkbook(fis);
        int pageSize = workBook.getNumberOfSheets();
        for (int i = 0; i < pageSize; i++) {
            HSSFSheet sheet = workBook.getSheetAt(i);
            ParserUtils.parseSheet(sheet);

        }

        kontenjans.sort(null);

        for(Kontenjan kont : kontenjans){
            System.out.println(kont);
        }




    }
}
