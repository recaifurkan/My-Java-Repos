package com.byrfb.net;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import com.byrfb.jsLibraries.Library;
import com.byrfb.net.json.parsers.JSONParser;
import com.byrfb.net.objects.Post;


public class GetRequest {
	
	public static void request(String url) {
		HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
		HttpRequest httpRequest = requestBuilder.newRequest().method(HttpMethods.GET).url(url).build();
		Gdx.net.sendHttpRequest(httpRequest, new HttpResponseListener() {
			
			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
//				Gdx.app.log("Log", httpResponse.getResultAsString());
//				Library.consoleLog(httpResponse.getResultAsString());
				
//				JSONParser parser = new JSONParser();
//				HashMap<String, Object> mapValue =  (HashMap<String, Object>) parser.parseJson(httpResponse.getResultAsString());
//				ArrayList<Object> lists = (ArrayList<Object>) mapValue.get("root");
//				mapValue = (HashMap<String, Object>) lists.get(0);
//				int id =  Integer.parseInt((String)mapValue.get("id"));
//				System.out.println(id);
				
				
				JSONParser parser = new JSONParser();
				HashMap<String, Object> mapValue =  (HashMap<String, Object>) parser.parseJson(httpResponse.getResultAsString());
				String text = (String) ((HashMap<?, ?>)((ArrayList<?>)mapValue.get("root")).get(0)).get("id");
				System.out.println(text + "dasdsa");
				for (Entry<String, Object> entry : mapValue.entrySet()) {
					
					System.out.println(entry.getClass().getName());
					Library.consoleLog(text);
					
				}
				
				
				
			
				
			}
			
			@Override
			public void failed(Throwable t) {
				System.out.println("fail");
			}
			
			@Override
			public void cancelled() {
				System.out.println("cancel");
				
			}
		});
	}

}

