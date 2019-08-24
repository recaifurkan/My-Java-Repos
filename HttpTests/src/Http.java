import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/*
 * bu projede yapt���m�z custom siteye yaz�lan yaz�y�  g�nderebiliyoruz
 * bununla ddaha fakl� �eyler yapab�liri
 * zaten http get post g�ndermeyi biliyosun burdan ay� �ekilde kullanrak g�nderebilirsin
 * 
 * 
 */

public class Http {

	private final String USER_AGENT = "Mozilla/5.0";

	String url = "http://www.myfiledepos.tk/getText";

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		Http http = new Http();

		while (true) {
			String text = sc.nextLine();
			try {
				http.sendPost(text);
			}

			catch (Exception e) {
				e.printStackTrace();

			}

		}

//        System.out.println("\nTesting 2 - Send Http POST request");
//        http.sendPost();

	}

	// HTTP GET request
	private void sendGet(String sendingText) throws Exception {
		System.out.println("Testing 1 - Send Http GET request");

		URL obj = new URL(url + sendingText);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url + sendingText);
		System.out.println("Response Code : " + responseCode);

//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
		Scanner sc = new Scanner(con.getInputStream());

//        File anasayfan = new File("anasayfa.html");
//        FileWriter writer = new FileWriter(anasayfan);
		String line;

		while (sc.hasNext()) {
			line = sc.nextLine();
//            		+ "\n";
			System.out.println(line);
//            writer.write(line);
//            writer.flush();

			try {
				Thread.sleep(0);
			} catch (Exception e) {

			}

		}
		sc.close();
//        writer.close();
		// print result
//        System.out.println(response.toString());

	}

	// HTTP POST request
	private void sendPost(String sendingText) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "text=" + sendingText;

		// Send post request
		con.setDoOutput(true);
		PrintWriter wr = new PrintWriter(con.getOutputStream());
		wr.write(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
			response.append(inputLine);
		}
		in.close();

		// print result
//        System.out.println(response.toString());

	}

}
