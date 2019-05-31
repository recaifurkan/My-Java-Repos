package net.specificsenders;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import java.util.Map;

public class PostSender extends Sender {

	// HTTP POST request
	public void sendInput(String sendingText) throws Exception {
		sendWithParamName("text", sendingText);

	}

	/*
	 * 
	 * alttaki metot tek bi tane deðiþken gönderilcekse onu alýr
	 */

	public void sendWithParamName(String paramName, String paramValue) throws IOException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(paramName, paramValue);
		sendWithParams(params);

	}

	/*
	 * 
	 * 
	 * alttaki metot post gönderiri ancak deðiþkenleri map þeklinde alýp multi bi
	 * þekilde gönderir
	 */

	public void sendWithParams(Map<String, Object> parametreler) throws IOException {

		URL url = new URL(this.url);

		Map<String, Object> params = parametreler;

		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0)
				postData.append('&');
			postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		}
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");

		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		con.setDoOutput(true);
		con.getOutputStream().write(postDataBytes);

		getResponse(con);

	}

	/*
	 * 
	 * 
	 * 
	 * alttaki ise geri dönen responseyi alýp basar
	 */

}
