package response;

import main.Main;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    PrintWriter out = null;
    BufferedOutputStream dataOut = null;

    Map<String, Object> headerParameters = new HashMap<String, Object>();


    boolean isResponsed = false;

    OutputStream connection;

    public HttpResponse(OutputStream connection) {
        this.connection = connection;
        // we get character output stream to client (for headers)
        out = new PrintWriter(connection);
        // get binary output stream to client (for requested data)
        dataOut = new BufferedOutputStream(connection);

//		sendFile("index.html");
    }

    /*
     * closing connections
     *
     */
    public boolean closeConnections() {
        try {

            out.write("\r\n");
            out.close();
            dataOut.close();
            if (Main.debug) {
                System.out.println("Connection closed.\n");
            }
            return true;
//			connection.close(); // we close socket connection
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /*
     * sending only headers
     *
     */
    public void sendHeaders() {
        if (isResponsed == false) {

            for (Map.Entry<String, Object> param : headerParameters.entrySet()) {
                StringBuilder headerContent = new StringBuilder();
                headerContent.append(param.getKey());
                headerContent.append(": ");
                headerContent.append(param.getValue());
                out.println(headerContent.toString());
            }
//			out.println(); // blank line between headers and content, very important !
            out.write("\r\n");
            out.write("\r\n");

            out.flush(); // flush character output stream buffer
            isResponsed = true;
            closeConnections();
        }

    }


    /*
     * sending only text
     *
     */
    public void sendText(String text) {

        if (isResponsed == false) {
            out.println("HTTP/1.1 " + "200 OK");
            out.println("main.Server: Java HTTP main.Server from Rfb : 1.0");
            out.println("Date: " + new Date());

            for (Map.Entry<String, Object> param : headerParameters.entrySet()) {
                StringBuilder headerContent = new StringBuilder();
                headerContent.append(param.getKey());
                headerContent.append(": ");
                headerContent.append(param.getValue());
                out.println(headerContent.toString());
            }
            out.write("\r\n");
            out.write("\r\n");
            out.println("<html>"+ text+ "</html>");
            out.flush(); // flush character output stream buffer
            isResponsed = true;
            closeConnections();
        }

    }


    /*
     * sending file with custom header values
     *
     *
     */
    public void sendFile(String durum, String fileName) {

        if (isResponsed == false) {
            File file = new File(Main.WEB_ROOT, fileName);
            int fileLength = (int) file.length();
            String content = getContentType(fileName);
            try {
                byte[] fileData = readFileData(file, fileLength);

                // send HTTP Headers

                out.println("HTTP/1.1 " + durum);
                for (Map.Entry<String, Object> param : headerParameters.entrySet()) {
                    StringBuilder headerContent = new StringBuilder();
                    headerContent.append(param.getKey());
                    headerContent.append(": ");
                    headerContent.append(param.getValue());
                    out.println(headerContent.toString());
                }

                out.println("main.Server: Java HTTP main.Server from Rfb : 1.0");
                out.println("Date: " + new Date());
                out.println("Content-type: " + content);
                out.print("Content-length: " + fileLength);
                out.write("\r\n");
                out.write("\r\n");
                out.println(); // blank line between headers and content, very important !
                out.flush(); // flush character output stream buffer

                dataOut.write(fileData, 0, fileLength);
                dataOut.flush();
                isResponsed = true;
                closeConnections();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    public void sendFile(String fileName) {

//		
        this.sendFile("200 OK", fileName);

    }

    public void sendAnasayfa() {

//		
        this.sendFile("200 OK", "index.html");

    }

    public void fileNotFound(String fileRequested) throws IOException {

        this.sendFile("404 File Not Found", Main.FILE_NOT_FOUND);

        if (Main.debug) {
            System.out.println("File " + fileRequested + " not found");
        }
    }

    public void methodNotAllowed(String method) throws IOException {
        if (Main.debug) {
            System.out.println("501 Not Implemented : " + method + " method.");
        }

        this.sendFile("501 Not Implemented", Main.METHOD_NOT_SUPPORTED);
    }

    private byte[] readFileData(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        byte[] fileData = new byte[fileLength];

        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        } finally {
            if (fileIn != null)
                fileIn.close();
        }

        return fileData;
    }

    // return supported MIME Types
    private String getContentType(String fileRequested) {
        if (fileRequested.endsWith(".htm") || fileRequested.endsWith(".html"))
            return "text/html";
        else
            return "text/plain";
    }

    public Map<String, Object> getHeaderParameters() {
        return headerParameters;
    }

}
