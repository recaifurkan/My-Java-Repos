package ownflasprocessors;

import java.io.File;
/*
 * i�ine kopyala.txt koydu�un klas�r� kopyalar
 * 
 * 
 * 
 */

import FlashCopier.DirectoryCopier;
import FlashCopier.Main;

/*
 * bu s�n�f i�inde kopyala.txt ad�nda dosya bulunduran klas�r varsa o ddosyay� kopyalar
 * 
 * 
 * 
 */

public class OwnFlashCopier {
	
	File ownFile;
	String kopyalamaBelirteci = "kopyala.txt";
	DirectoryCopier copier;
	
	
	
	
	public OwnFlashCopier(File ownPath) {
		super();
		this.ownFile = ownPath;
		copier = new DirectoryCopier();
		loopFolder(new File(Main.destPath));
		
	}
	
	/*
	 * bu metot i�te verilen klas�rde d�ner i�aretlenmi� dosya var m� diye bakar
	 * varsa kopyalamaya g�nderir
	 * 
	 * 
	 * 
	 */

	private void loopFolder(File source) {
		if (source.isDirectory()) {
//			System.out.println(source.getAbsolutePath());
			
			String files[] = source.list();

			for (String file : files) {
				File srcFile = new File(source, file);
				loopFolder(srcFile);
			}
			
			File belirtecFile = new File(source , kopyalamaBelirteci);
			if(belirtecFile.exists()) {
				System.out.println("kopyalancak klas�r bulundu" + source.getAbsolutePath());
				File destFile =  new File(ownFile, source.getName());
				OwnSolver.progress.progress(source);
				copier.copyFolder(source,destFile) ;
			}
		} 
	}



}
