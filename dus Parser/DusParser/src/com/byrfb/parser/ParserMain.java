package com.byrfb.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.byrfb.objects.Kontenjan;

public class ParserMain {

	private static String bolum = "�ocuk";

	public static void main(String[] args) throws IOException {
		File input = new File("assets/minmax(genel)05112018_4.xlsx");
		FileInputStream fis = new FileInputStream(input);
		
		ArrayList<Kontenjan> kontenjans = new ArrayList<Kontenjan>();

		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workBook.getSheetAt(0);

		Iterator<Row> rowIterator = sheet.iterator();

		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			int col = 0;
			
//			System.out.println(cell);
			Kontenjan kont = new Kontenjan();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if(cell.toString().equals("---")) {
					col++;
					continue;
				}
				if (col > 5)
					break;
				
				switch (col) {
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
					kont.setEnKucuk( Float.parseFloat(cell.toString().replace(",", ".")));
					break;
				case 5:
					kont.setEnBuyuk(Float.parseFloat(cell.toString().replace(",", ".")));
					break;

				default:
					break;
				}
				
				
				col++;
				

			}
//			System.out.println(kont);
			
//			&& kont.getBrans().indexOf(bolum) != -1
			if(kont.getUniversite() != null && kont.getKontenjanSayisi() != 0 )
				if(kont.getBrans().indexOf(bolum) != -1) {
					kontenjans.add(kont);
				}
				
				
			
			

		}
		
		Kontenjan.sortIndex = 3;
		kontenjans.sort(null);
		
		
		
		fis.close();
		
		File saveFile = new File("assets/sortedKontenjans.html");
		saveFile.createNewFile();
		StringBuilder builder = new StringBuilder();
		builder.append("<table style = '"
				+ "margin-left: auto;" + 
				"  margin-right: auto;'>");
		for (Kontenjan konte : kontenjans) {
			builder.append("<tr>");
			builder.append("<td>");
			builder.append(
					"�niversite : " + konte.getUniversite()
			);
			builder.append("</td>");

			builder.append("<td>");

			builder.append(
					" -Bran� : " + konte.getBrans()
			);
			builder.append("</td>");

			builder.append("<td>");

			builder.append(
					" -En K���k Puan : " + konte.getEnKucuk()
			);
			builder.append("</td>");
			builder.append("</tr>");
		}
		builder.append("</table>");
		PrintWriter writer = new PrintWriter(saveFile);
		writer.write(builder.toString());
		writer.close();

	}

}