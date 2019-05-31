package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import java.util.StringTokenizer;

import main.Main;
import request.GetRequest;
import request.PostRequest;
import request.Request;
import response.HttpResponse;

public class HttpRequest {

	StringBuilder requestText = new StringBuilder();

	Map<String, Object> headerParameters = new HashMap<String, Object>();

	Request request;

	String requestPath;

	HttpResponse response;

	InputStreamReader isReader;
	BufferedReader br;

	public HttpRequest(InputStream connection, HttpResponse response) throws IOException {

		this.response = response;

		isReader = new InputStreamReader(connection);

		br = new BufferedReader(isReader);

		// alttakiyle en baþta gelenin methodu için okuma yapýlýyor
		String methodLine = br.readLine();
		readFromComing();

		StringTokenizer parse = new StringTokenizer(methodLine);
		String method = parse.nextToken().toUpperCase(); // we get the HTTP method of the client
		requestPath = parse.nextToken().toLowerCase();
		String standart = parse.nextToken();
		// bi daha gelen bütün verilerin okunmasý yapýlýyor

// 		metoun seçimi yapýlýyor
		chooseMethod(method);

	}

	/*
	 * 
	 * gelen baðlantýnýn headerini okur return olarak da gelen ilk satýrý yani
	 * methodun falan yazdýðý kýsmý döndürðr
	 */

	private void readFromComing() throws IOException {
		// code to read and print headers
		String headerLine = null;
		while ((headerLine = br.readLine()) != null && headerLine.length() != 0) {
			requestText.append(headerLine);
			addHeader(headerLine);

		}
	}

	/*
	 * 
	 * 
	 * gelen headirleri ekler iþte
	 */
	private void addHeader(String headerLine) {
		String headerParameters[] = headerLine.split(":", 2);

		this.headerParameters.put(headerParameters[0], headerParameters[1]);

	}

	/*
	 * gelen baðlantýnýn türü ne ona göre iþlem yapar
	 * 
	 * 
	 */
	private void chooseMethod(String method) throws IOException {
// url yi ayýrmak için kullanýlýyor get için parametreler url de
		String parameters = parseUrl();
		switch (method) {
		case "GET":

			request = new GetRequest(parameters);
//			sendParameters();

			break;
		case "POST":
			StringBuilder postParameters = new StringBuilder();
			while (br.ready()) {
				postParameters.append((char) br.read());
			}
			parameters = postParameters.toString();
			request = new PostRequest(parameters);

//			sendParameters();

			break;

		default:
			if (Main.debug) {
				System.out.println("Desteklenmeyen Request Type - " + method);

			}
			response.methodNotAllowed(method);

			break;
		}

	}

	// url yi ayýrmak için kullanýlýyor get için parametreler url de
	private String parseUrl() {
		String[] requestWithGetParameters = requestPath.split("\\?", 2);
		requestPath = requestWithGetParameters[0];
		if (requestWithGetParameters.length > 1) {
			return requestWithGetParameters[1];
		}
		return "";

	}

	/*
	 * debug için parametreleri göndermeye yarýyor
	 * 
	 */
	private void sendParameters() {
		if (Main.debug) {
			StringBuilder build = new StringBuilder();
			for (Map.Entry<String, Object> param : getParameters().entrySet()) {
				build.append(param.getKey());
				build.append("-");
				build.append(param.getValue());

			}

			response.sendText(build.toString());
		}

	}

	public Map<String, Object> getHeaderParameters() {
		return headerParameters;
	}

	public void setHeaderParameters(Map<String, Object> headerParameters) {
		this.headerParameters = headerParameters;
	}

	public StringBuilder getRequestText() {
		return requestText;
	}

	public Map<String, Object> getParameters() {
		return request.parameters;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	public void closeConnections() throws IOException {
		isReader.close();
		br.close();
		if (Main.debug) {
			System.out.println("Closing request connections");
		}
	}
	
	public String getParameter(String key) {
		Object sonuc = this.request.parameters.get(key);
		if(sonuc != null) {
			return (String) sonuc;
		}
		
		return "-*-";
	}

}
