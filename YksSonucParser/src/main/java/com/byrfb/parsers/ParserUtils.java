package com.byrfb.parsers;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;

public class ParserUtils {

    static Parser<HSSFSheet> sheetParserarser = new SheetParser();
    static Parser<Row> rowParser = new RowParser();

    public static void parseSheet(HSSFSheet sheet){

        sheetParserarser.parse(sheet);
    }

    public static void parseRow(Row row){


        rowParser.parse(row);
    }




}
