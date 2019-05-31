package ownflasprocessors;

import java.io.File;
import java.io.IOException;

public class CopyProcessProgressor {

	File nowFile;
	
	File pastFile;

	File flashFile;
	String tamamlandi = "tamamlandý";

	public CopyProcessProgressor(File flashFile) {
		super();
		this.flashFile = flashFile;
		File tamamlandiFile = new File(flashFile.getAbsolutePath(), tamamlandi);
		if(tamamlandiFile.exists()) {
			tamamlandiFile.delete();
		}
	}

	public void progress(File progressFile) {

		String durumCumle = "basladý";
		if(pastFile != null && pastFile.exists()) {
			pastFile.delete();
		}
		
		if (progressFile == null) {
			durumCumle = tamamlandi;
		} else {

			durumCumle = ("kopyalanýyor- " + progressFile.getName());
		}

		nowFile = new File(flashFile.getAbsolutePath(), durumCumle);
		try {
			nowFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pastFile = nowFile;
		
	}
}
