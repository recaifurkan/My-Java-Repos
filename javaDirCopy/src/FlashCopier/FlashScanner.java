package FlashCopier;

import java.io.File;
import java.util.Arrays;

import ownflasprocessors.OwnSolver;

public class FlashScanner {
	File[] tempComputerRoots;
	File[] oldListRoot = File.listRoots();
	File[] newListRoot;
	int waitTime = 1000;

	private void memoryRoots() {
		this.tempComputerRoots = File.listRoots();
	}

	public void waitForNotifying() {
		this.memoryRoots();
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					newListRoot = File.listRoots();
					if (oldListRoot.length < newListRoot.length) {
						flashEklendi(fazlayiBul(newListRoot, oldListRoot));

					} else if (oldListRoot.length > newListRoot.length) {
						// flash çýkartýldý baba ona göre iþlem yapýlacak
						flashCikartildi(fazlayiBul(oldListRoot, newListRoot));

					} else {

					}

					oldListRoot = File.listRoots();

				}
			}
		});
		t.start();
	}

	// bu method 2 tane array verdiðinde tabi ki  bir tanesi diðerinden içinde bulunacak
	// aralarýnaki fazla olaný bulu p return eder
	private File fazlayiBul(File[] fazlaOlan, File[] eksikOlan) {
		String fazlaFile = "";
		boolean fileVar = false;
		for (File fazla : fazlaOlan) {
			for (File eksik : eksikOlan) {
				if (fazla.equals(eksik)) {
					fileVar = true;
					break;
				}
			}
			if (fileVar) {
				fileVar = false;
				continue;
			} else {
				fazlaFile = fazla.getPath();
			}

		}
		return new File(fazlaFile);

	}

	private void flashEklendi(final File newListRoot2) {
		System.out.println("takýldý " + newListRoot2.getAbsolutePath());
		new Thread(new Runnable() {

			@Override
			public void run() {
				OwnSolver solver = new OwnSolver(newListRoot2);

			}
		}).start();

		// TODO Auto-generated method stub

	}

	private void flashCikartildi(File newListRoot2) {
		// TODO Auto-generated method stub
		System.out.println("çýkartýldý " + newListRoot2.getAbsolutePath());

	}

}
