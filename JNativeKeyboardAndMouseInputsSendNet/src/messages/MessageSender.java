package messages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import ComputerNameSettings.ComputerNameSolver;
import net.Net;

public class MessageSender {
	long duzenliSendingTime;
	Net net;
	File gonderilecekTextFile;

	public MessageSender() {
		net = new Net();
		duzenliSendingTime = 1000 * 60 * 60;

		gonderilecekTextFile = new File("message.txt");
		new Timer().scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println("Gonderiliyor");

				FileInputStream fis = null;
				try {
					fis = new FileInputStream(gonderilecekTextFile);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
				Scanner textScanner = new Scanner(fis);
				StringBuilder allText = new StringBuilder();
				while (textScanner.hasNext()) {
					allText.append(textScanner.next());
				}
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("computerName", ComputerNameSolver.getComputerName());
				params.put("text", allText.toString());
				try {
					net.getPostSender().sendWithParams(params);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					fis.close();
					textScanner.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				gonderilecekTextFile.delete();
//				if() {
//					System.out.println("file deleted");
//				}
//				else {
//					System.out.println("silinemedi");
//				}
//				System.out.println("Gonderildi");

			}
		}, 0, duzenliSendingTime);
	}

}
