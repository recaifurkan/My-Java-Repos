package com.byrfb.ffmpeg;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Mp42Ogv {
	
	static File inputFile;
	static File outputFile;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		 JFileChooser chooser = new JFileChooser();
	        FileNameExtensionFilter filter = new FileNameExtensionFilter(
	                "Mp4 File", "mp4");
	        chooser.setDialogTitle("Mp4 File");
	        chooser.setFileFilter(filter);
	        int returnVal = chooser.showOpenDialog(null);
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	        	inputFile = chooser.getSelectedFile();
	        	
	            System.out.println("You chose to open this file: " +
	                    chooser.getSelectedFile());
	        }
	        
	        
	        FileNameExtensionFilter filter2 = new FileNameExtensionFilter(
	                "Ogv File", "ogv");
	        chooser.setDialogTitle("Ogv File");
	        chooser.setFileFilter(filter2);
	        returnVal = chooser.showOpenDialog(null);
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	        	outputFile = chooser.getSelectedFile();
	        	
	            System.out.println("You chose to open this file: " +
	                    chooser.getSelectedFile());
	        }
	        
	     if(inputFile == null || outputFile == null) {
	    	 System.out.println("Dosya yok");
	    	 System.exit(404);
	     }
	        
	        
	        
	     
//	     ProcessBuilder processBuilder = new ProcessBuilder();
//	     processBuilder.command();
//	     
//	     processBuilder.redirectOutput(Redirect.PIPE);
//
//	     processBuilder.directory(new File(System.getProperty("user.dir").replace("\\", "/") + "/lib"));
//	     Process process = processBuilder.start();
//	     int waitFlag = process.waitFor();// Wait to finish application execution.
//	     if (waitFlag == 0) {
//	     
//	      returnVal = process.exitValue();
//	      System.out.println("Converted With status Code : " + returnVal);
//	     ffmpeg -i input.mp4 -c:v libtheora -q:v 7 -c:a libvorbis -q:a 4 output.ogv
//	     } 
	     
	     String command = "lib/ffmpeg64.exe -i "+inputFile.getAbsolutePath()+" -c:v libtheora -q:v 7 -c:a libvorbis -q:a 4 "+outputFile.getAbsolutePath();
//	     Process process = new ProcessBuilder(command.replace("\\", "/")).start();
//	     InputStream is = (InputStream) process.getInputStream();
//	     InputStreamReader isr = new InputStreamReader(is);
//	     BufferedReader br = new BufferedReader(isr);
//	     String line;
//
//	     System.out.printf("Output of running %s is:", Arrays.toString(args));
//
//	     while ((line = br.readLine()) != null) {
//	       System.out.println(line);
//	     }
	     
	     String line;
	      Process p = Runtime.getRuntime().exec(command);
//	      BufferedReader bri = new BufferedReader
//	        (new InputStreamReader(p.getInputStream()));
//	      BufferedReader bre = new BufferedReader
//	        (new InputStreamReader(p.getErrorStream()));
//	      while ((line = bri.readLine()) != null) {
//	        System.out.println(line);
//	      }
//	      bri.close();
//	      while ((line = bre.readLine()) != null) {
//	        System.out.println(line);
//	      }
//	      bre.close();
//	      p.waitFor();
	      System.out.println("Done.");
		

		
		

		
	}

}
