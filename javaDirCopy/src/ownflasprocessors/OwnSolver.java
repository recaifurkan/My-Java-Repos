package ownflasprocessors;

import java.io.File;
import java.io.IOException;

import FlashCopier.DirectoryCopier;

public class OwnSolver {
	/*
	 * bu sýnýf baðlanan flasha göre iþlem yapar
	 * bakýyo iþte flash senin mi deilm mi diye
	 * seninse zaten kopyalamaz
	 * senin deðilse anasýný avradýný siker
	 * 
	 * 
	 */
	
	File sourceFile;
	
	String ownBelirtec = "ownFlash.txt";
	
	public static CopyProcessProgressor progress ;

	public OwnSolver(File ownPath) {
		
		this.sourceFile = ownPath;
		progress = new CopyProcessProgressor(sourceFile);
		File ownBelirtecFile = new File(sourceFile,ownBelirtec);
		System.out.println(ownBelirtecFile.getAbsolutePath());
		
		if(ownBelirtecFile.exists()) {
//			System.out.println("File Bulundu");
			
			OwnFlashCopier copier = new OwnFlashCopier(sourceFile);
			progress.progress(null);
			
				
		}
		else {
			new DirectoryCopier(sourceFile.getAbsolutePath());
		}
	}
	
	

}
