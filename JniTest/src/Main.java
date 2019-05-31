import java.io.File;


/*
 * Baba  bu projede c++ ile �ok g�zel bi�ekilde haberle�meyi yapt�n java ile cpp ba�lant�sn� da art�k kolay olu�turursu
 * 
 * 
 */

public class Main {
	static {
		File library = new File("JNIDll\\x64\\Debug\\Dll1.dll");
		System.load(library.getAbsolutePath());
	}

	public static void main(String[] args) {
		Main main = new Main();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					while (!main.ishandled()) {
						System.out.println(main.getKEy());
					}
					try {
						Thread.sleep(1000/60);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();

		if(main.helloWorld("recaiii") == 5) {
			System.exit(1);
			
		}

	}

	native int helloWorld(String isim);

	native int getKEy();

	native boolean ishandled();

}
