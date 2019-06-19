package com.byrfb.main;


import java.awt.Color;

/*
 * 
 * bu projede png olarak aldýðýmýz resmi excell satýr ve sütuna dönüþtürerek çizim
 * yapýldý
 * baþta res,min piksel deðeri alýndý
 * sonra pikselin rengine bakýlýyor
 * çünkü excell her defasýnda farklý style oluþturmaya izin vermiyor
 * o yüzden piksel rengi var mý diye balýyýlýo
 * yoksa yeni oluþturulup iþlem yapýlýyor
 * en son excell kayý yapýlýyor
 * 
 * 
 * 
 * 
 * 
 */
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		  XSSFWorkbook wb = new XSSFWorkbook();
		    XSSFSheet sheet = wb.createSheet();
		    
		    BufferedImage image = ImageIO.read(new File("./resim4.png"));
		    int xAdet = image.getWidth();
		    int yAdet = image.getHeight();
		    
		    ArrayList<ColorStyle> colors = new ArrayList<>();


	        for (int y = 0; y < image.getHeight(); y++) {
	        	XSSFRow row = sheet.createRow(y);
	            for (int x = 0; x < image.getWidth(); x++) {
	            	Color color = new Color(image.getRGB(x, y));
	            	XSSFCellStyle style = null;
	            	for(ColorStyle col:colors) {
	            		if(col.color.getRGB() == color.getRGB()) {
	            			style = col.style;
	            			break;
	            		}
	            			            		
	            	}
	            	
	            	if(style == null) {
	            		style  = wb.createCellStyle();
	            		colors.add(new ColorStyle(color, style));
	            	}
	            	
//	            	threads.add(new CellSetter(wb, sheet, row ,x,y,image));
	            	
	        		XSSFCell cell = row.createCell( x);
	        	    style.setFillForegroundColor(new XSSFColor(color, new DefaultIndexedColorMap()));
	        	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	                cell.setCellStyle(style);
	                System.out.println("cell added" + cell.getColumnIndex() + "-" + cell.getRowIndex());
	            	
	            }
	        }
//		    for(Thread th:threads) {
//		    	th.start();
//		    }
//		    
//		    boolean isFinish = false;
//		    while(!isFinish) {
//		    	isFinish = true;
//		    	  for(Thread th:threads) {
//				    	if(th.isAlive()) {
//				    		isFinish = false;
//				    		break;
//				    	}
//				    	
//				    }
//		    }

	        File file = new File("./cp.xlsx");
	        file.createNewFile();
		    FileOutputStream fos =new FileOutputStream(file);
		    wb.write(fos);
		    fos.close();
		    System.out.println("Done");
		

	}

}

class ColorStyle{
	Color color;
	XSSFCellStyle style;
	public ColorStyle(Color color, XSSFCellStyle style) {
		super();
		this.color = color;
		this.style = style;
	}
	
}

//class CellSetter extends Thread{
//	
//	 XSSFWorkbook wb;
//	 XSSFSheet sheet;
//	 XSSFRow row;
//	 BufferedImage image;
//	 int x,y;
//    public CellSetter(XSSFWorkbook wb, XSSFSheet sheet, XSSFRow row, int x,int y,BufferedImage image) {
//		super();
//		this.wb = wb;
//		this.sheet = sheet;
//		this.row = row;
//		this.x = x;
//		this.image = image;
//		this.y = y;
//		
//	}
//
//	@Override
//	public void run() {
//		XSSFCellStyle  style = wb.createCellStyle();
//		XSSFCell cell = row.createCell( x);
//	    style.setFillBackgroundColor(new XSSFColor(new Color(image.getRGB(x, y))));
//        cell.setCellStyle(style);
//        System.out.println("cell added" + cell.getColumnIndex() + "-" + cell.getRowIndex());
//	}
//}
