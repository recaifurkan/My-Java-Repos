package com.byrfb.parser;

import com.byrfb.Util;
import com.byrfb.objects.Kontenjan;
import com.byrfb.objects.KontenjanBolum;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;


public class ParserMain {


    KontenjanBolum bolum;
    ArrayList<Kontenjan> kontenjans = new ArrayList<Kontenjan>();
    XSSFWorkbook workBook;

    public ArrayList<Kontenjan> getKontenjans() {
        return kontenjans;
    }

    public void setKontenjans(ArrayList<Kontenjan> kontenjans) {
        this.kontenjans = kontenjans;
    }

    public void save(ArrayList<Kontenjan> requestSave) throws IOException {

        File saveFile = new File("assets/" + bolum.getText() + ".html");
        saveFile.createNewFile();

        String template = Util.open("assets/bas.html");

        template = template.replace("{{@Title}}", bolum.getText());


        String tableText = createTable(requestSave);

        template = template.replace("{{@Body}}", tableText);
        PrintWriter writer = new PrintWriter(saveFile);
        writer.write(template);
        writer.close();


    }

    private String createTable(ArrayList<Kontenjan> requestSave) {
        StringBuilder builder = new StringBuilder();
        builder.append("<table style = '"
                + "margin-left: auto;" +
                "  margin-right: auto;'>");
        for (Kontenjan konte : requestSave) {
            convertTextKontenjan(builder, konte);


        }
        builder.append("</table>");
        return builder.toString();
    }

    private void convertTextKontenjan(StringBuilder builder, Kontenjan konte) {
        builder.append("<tr>");
        //---------------------- Start
        builder.append("<td>");
        builder.append(
                "Universite : " + konte.getUniversite()
        );
        builder.append("</td>");
//---------------------- Start
        builder.append("<td>");

        builder.append(
                " -Brans : " + konte.getBrans()
        );
        builder.append("</td>");

        //---------------------- Start
        builder.append("<td>");

        builder.append(
                " -En Kucukk Puan : " + konte.getEnKucuk()
        );
        builder.append("</td>");

//---------------------- Start
        builder.append("<td>");
        builder.append(
                " -Kontenjan : " + konte.getKontenjanSayisi()
        );
        builder.append("</td>");
        builder.append("</tr>");
    }

    public void read() throws IOException {
        File input = new File("assets/minmaxgenelyu09122019.xlsx");
        FileInputStream fis = new FileInputStream(input);
        workBook = new XSSFWorkbook(fis);
        fis.close();
    }

    public void bookParse() {
        int sheetLenght = workBook.getNumberOfSheets();

        for (int i = 0; i < sheetLenght; i++) {
            sheetParse(i);
        }
    }

    private void sheetParse(int sheetInderx) {
        XSSFSheet sheet = workBook.getSheetAt(sheetInderx);

        Iterator<Row> rowIterator = sheet.iterator();
        int atlanacakRow = 0;

        Row row = null;
        while (rowIterator.hasNext()) {
            row = rowIterator.next();
            if (atlanacakRow < 3) {

                atlanacakRow++;
                continue;

            }
            try {
                parseRow(row);

            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }

        }
    }

    public void parseRow(Row row) throws ArrayIndexOutOfBoundsException {
        Iterator<Cell> cellIterator = row.cellIterator();
        int col = 0;

//			System.out.println(cell);
        Kontenjan kont = new Kontenjan();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (cell.toString().equals("---")) {
                col++;
                continue;
            }
            if (col > 5)
                break;

            switch (col - 1) {
                case 0:
                    String unFakBras = cell.toString();

                    String[] splittinCell = unFakBras.split("/");

                    kont.setUniversite(splittinCell[0]);
                    kont.setFakulte(splittinCell[1]);
                    kont.setBrans(splittinCell[2]);


                    break;
                case 1:
                    kont.setKontenjanSayisi(Float.parseFloat(cell.toString().replace(",", ".")));
                    break;
                case 2:
                    kont.setYerlesen(Float.parseFloat(cell.toString().replace(",", ".").replace("*", "")));
                    break;
                case 3:
                    kont.setBosKontenjan(Float.parseFloat(cell.toString().replace(",", ".")));
                    break;
                case 4:
                    kont.setEnKucuk(Float.parseFloat(cell.toString().replace(",", ".")));
                    break;
                case 5:
                    kont.setEnBuyuk(Float.parseFloat(cell.toString().replace(",", ".")));
                    break;

                default:
                    break;
            }


            col++;


        }


        if (kont.getUniversite() != null && kont.getKontenjanSayisi() != 0)
            kontenjans.add(kont);

    }

    public ArrayList<Kontenjan> reserve() {
        ArrayList<Kontenjan> reserved = new ArrayList<>();
        for (Kontenjan kont : kontenjans) {
            if (bolum == KontenjanBolum.Bolumsuz || kont.getBrans().toLowerCase().indexOf(bolum.name().toLowerCase()) != -1) {
                reserved.add(kont);

            }
        }
        return reserved;
    }


    public KontenjanBolum getBolum() {
        return bolum;
    }

    public void setBolum(KontenjanBolum bolum) {
        this.bolum = bolum;
    }


}
