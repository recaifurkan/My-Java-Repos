package ownflasprocessors;

import java.io.File;
/*
 * içine kopyala.txt koyduðun klasörü kopyalar
 * 
 * 
 * 
 */

import FlashCopier.DirectoryCopier;
import FlashCopier.Main;

/*
 * bu sýnýf içinde kopyala.txt adýnda dosya bulunduran klasör varsa o ddosyayý kopyalar
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
	 * bu metot iþte verilen klasörde döner iþaretlenmiþ dosya var mý diye bakar
	 * varsa kopyalamaya gönderir
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
				System.out.println("kopyalancak klasör bulundu" + source.getAbsolutePath());
				File destFile =  new File(ownFile, source.getName());
				OwnSolver.progress.progress(source);
				copier.copyFolder(source,destFile) ;
			}
		} 
	}



}
