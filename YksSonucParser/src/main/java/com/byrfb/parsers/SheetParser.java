package com.byrfb.parsers;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;

public class SheetParser implements Parser<HSSFSheet> {


    public void parse(HSSFSheet sheet) {


        int rowSize = sheet.getLastRowNum();

        for (int rowIndex = 3; rowIndex < rowSize; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            ParserUtils.parseRow(row);

        }


    }


}

