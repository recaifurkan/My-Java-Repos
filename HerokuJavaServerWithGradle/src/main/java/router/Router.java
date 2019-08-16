package router;

import main.Main;
import request.HttpRequest;
import response.HttpResponse;

import java.io.File;
import java.io.IOException;

public class Router {
    HttpRequest request;
    HttpResponse response;
    public Router(HttpRequest request, HttpResponse response) {
        this.request = request;
        this.response =response;
        route();
    }

    private void route(){
        File requestFile = new File(Main.WEB_ROOT, request.getRequestPath());
        // istenen dosya var m� diye kontrol ediliyor
        if (requestFile.exists()) {
            // e�er anasayfa istenmi�se diye bak�l�yor

            if (request.getRequestPath().equals("/")) {
                // url de rfb diye parametreli de�i�ken g�nderilmi� mi  bak�l�yor
//                    String sonuc = (String) request.getParameter("rfb");

//                    response.sendText(sonuc);
                response.sendAnasayfa();
            } else {
                response.sendFile(request.getRequestPath());
            }

        } else {
            try {
                response.fileNotFound(request.getRequestPath());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
