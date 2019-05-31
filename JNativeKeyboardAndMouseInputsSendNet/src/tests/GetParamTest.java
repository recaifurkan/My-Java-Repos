package tests;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetParamTest {

	public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("Ben", "Sen");
		params.put("Sen", "Ben");

		StringBuilder postData = new StringBuilder();

		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0)
				postData.append('&');
			else {
				postData.append("?");
			}
			postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));

		}

		String url = "www.facebook.com";
		URL obj = new URL(url + postData);
		System.out.println(obj.toString());

	}

}
