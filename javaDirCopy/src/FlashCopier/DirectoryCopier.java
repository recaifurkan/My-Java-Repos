package FlashCopier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.InputStream;
import java.io.OutputStream;

public class DirectoryCopier {
	File folder;
	File dest;

	String flashFakeName;

	public DirectoryCopier(String pathName) {
		this.folder = new File(pathName);
		flashFakeName = "data" + (int) (10000 * Math.random());

		dest = new File(Main.destPath + "\\" + flashFakeName);
		System.out.println("Flash kopyalanmaya baþladý " + pathName);
		this.copyFolder(this.folder, dest);

		System.out.println("Flash kopyalama tamamlandý buraya kopyalandý " + dest);

	}
	
	public DirectoryCopier() {
		

	}

	public void copyFolder(File source, File destination) {
		if (source.isDirectory()) {
			if (!destination.exists()) {
				destination.mkdirs();
			}

			String files[] = source.list();

			for (String file : files) {
				File srcFile = new File(source, file);
				File destFile = new File(destination, file);

				copyFolder(srcFile, destFile);
			}
		} else {
			System.out.println("Kopyalanmaya baþlanýyor " + source.getAbsolutePath());
			if (copyFile(source, destination)) {

				System.out.println("Kopyalanma tamamlandý  " + source.getAbsolutePath());
			} else {
				System.out.println("Kopyalanma tamamlanamadý  " + source.getAbsolutePath());
			}

		}
	}

	private boolean copyFile(File source, File destination) {
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(source);
			out = new FileOutputStream(destination);

			byte[] buffer = new byte[1024];

			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}

	}

}
