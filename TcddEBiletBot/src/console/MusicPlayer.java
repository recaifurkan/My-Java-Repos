package console;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author ByRfb
 */
public class MusicPlayer {

	FileInputStream fis;
	Player playMP3;

	public Player getPlayMP3() {
		return playMP3;
	}

	public void setPlayMP3(Player playMP3) {
		this.playMP3 = playMP3;
	}

	private Thread musicThread;

	public Thread getMusicThread() {
		return musicThread;
	}

	/**
	 *
	 * @param path
	 * @throws FileNotFoundException
	 * @throws JavaLayerException
	 * @throws InterruptedException
	 */
	public MusicPlayer() {

	}

	public void play() throws FileNotFoundException, JavaLayerException, InterruptedException {

		playSound("libs/music.mp3");
	}

	/**
	 *
	 * @param path
	 * @throws FileNotFoundException
	 * @throws JavaLayerException
	 * @throws InterruptedException
	 */
	public void playSound(String path) throws FileNotFoundException, JavaLayerException, InterruptedException {
		fis = new FileInputStream(path);
		playMP3 = new Player(fis);

		musicThread = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					playMP3.play();

				} catch (JavaLayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		musicThread.start();

	}

}
