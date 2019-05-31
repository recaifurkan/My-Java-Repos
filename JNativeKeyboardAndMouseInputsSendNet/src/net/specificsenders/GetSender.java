package net.specificsenders;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class GetSender extends Sender {

	// HTTP GET request

	@Override
	public void sendWithParams(Map<String, Object> parametreler) throws IOException {
//		System.out.println("Testing 1 - Send Http GET request");

		Map<String, Object> params = parametreler;

		StringBuilder getData = new StringBuilder();

		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (getData.length() != 0)
				getData.append('&');
			else {
				getData.append("?");
			}
			getData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			getData.append('=');
			getData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));

		}

		URL obj = new URL(url + getData);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		getResponse(con);

	}

}
