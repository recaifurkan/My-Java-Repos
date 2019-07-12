package com.byrfb.net.tests;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.net.HttpRequestBuilder;





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

