package request;

import main.Main;
import response.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class HttpRequest {

    StringBuilder requestText = new StringBuilder();

    Map<String, Object> headerParameters = new HashMap<String, Object>();

    HttpResponse response;

    String requestPath;


    InputStreamReader isReader;
    BufferedReader br;
    private Request request;

    public HttpRequest(InputStream connection, HttpResponse response) throws IOException {

        this.response = response;

        isReader = new InputStreamReader(connection);

        br = new BufferedReader(isReader);

        // alttakiyle en ba�ta gelenin methodu i�in okuma yap�l�yor
        String methodLine = br.readLine();
        readFromComing();


        StringTokenizer parse = new StringTokenizer(methodLine);
        String method = parse.nextToken().toUpperCase(); // we get the HTTP method of the client
        requestPath = parse.nextToken().toLowerCase();
        String standart = parse.nextToken();
        // bi daha gelen b�t�n verilerin okunmas� yap�l�yor

// 		metoun se�imi yap�l�yor
        chooseMethod(method);

    }

    /*
     *
     * gelen ba�lant�n�n headerini okur return olarak da gelen ilk sat�r� yani
     * methodun falan yazd��� k�sm� d�nd�r�r
     */

    private void readFromComing() throws IOException {
        // code to read and print headers


        String headerLine = null;
        while ((headerLine = br.readLine()) != null) {
            if (headerLine.isEmpty()) {
                break;
            }
            requestText.append(headerLine);
            addHeader(headerLine);


        }
    }

    /*
     *
     *
     * gelen headirleri ekler i�te
     */
    private void addHeader(String headerLine) {
        String headerParameters[] = headerLine.split(":", 2);

        this.headerParameters.put(headerParameters[0], headerParameters[1]);

    }

    /*
     * gelen ba�lant�n�n t�r� ne ona g�re i�lem yapar
     *
     *
     */
    private void chooseMethod(String method) throws IOException {
// url yi ay�rmak i�in kullan�l�yor get i�in parametreler url de
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

    // url yi ay�rmak i�in kullan�l�yor get i�in parametreler url de
    private String parseUrl() {
        String[] requestWithGetParameters = requestPath.split("\\?", 2);
        requestPath = requestWithGetParameters[0];
        if (requestWithGetParameters.length > 1) {
            return requestWithGetParameters[1];
        }
        return "";

    }

    /*
     * debug i�in parametreleri g�ndermeye yar�yor
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
        if (sonuc != null) {
            return (String) sonuc;
        }

        return "-*-";
    }

}
