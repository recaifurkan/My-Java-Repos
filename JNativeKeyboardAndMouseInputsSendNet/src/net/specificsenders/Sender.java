package net.specificsenders;

import java.io.IOException;
import java.net.HttpURLConnection;

import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;

import security.EncryptedFileReader;

public abstract class Sender {
	protected String url;

	public Sender() {

		url = new EncryptedFileReader().decryptFileContent("./url.txt");

	}

	protected final String USER_AGENT = "Mozilla/5.0";

	public void sendInput(String sendingText) throws Exception {
		sendWithParamName("text", sendingText);

	}

	/*
	 * 
	 * alttaki metot tek bi tane de�i�ken g�nderilcekse onu al�r
	 */

	public void sendWithParamName(String paramName, String paramValue) throws IOException, Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(paramName, paramValue);
		sendWithParams(params);

	}

	/*
	 * 
	 * 
	 * alttaki metot post g�nderiri ancak de�i�kenleri map �eklinde al�p multi bi
	 * �ekilde g�nderir
	 */

	abstract void sendWithParams(Map<String, Object> parametreler) throws IOException;

	void getResponse(HttpURLConnection con) throws IOException {
		int responseCode = con.getResponseCode();
		Scanner sc = new Scanner(con.getInputStream());
		String line;
		StringBuilder text = new StringBuilder();

		while (sc.hasNext()) {
			line = sc.nextLine();
			text.append(line);

			try {
				Thread.sleep(0);
			} catch (Exception e) {

			}

		}
		sc.close();
		System.out.println(text);

	}

}
