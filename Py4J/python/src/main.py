"""
Created on Dec 19, 2009

@author: Recai Furkan Bostancı
"""
from py4j.java_gateway import JavaGateway


gateway = JavaGateway()
jvm = gateway.jvm

ArrayList = jvm.java.util.ArrayList
Io = jvm.java.io
HttpURLConnection = jvm.java.net.HttpURLConnection
Url = jvm.java.net.URL
Scanner = jvm.java.util.Scanner
String = jvm.java.lang.String
System = jvm.java.lang.System
Main = jvm.com.byrfb.Main
Http = jvm.com.byrfb.Http

if __name__ == "__main__":
    USER_AGENT = String("Mozilla/5.0")
    url = String("https://www.facebook.com")
    http = Http(url)
    http.sendGet("")

    # while 1:
    #     sc = Scanner(Main.getSystemIn());
    #     text = sc.nextLine()
    #     print(text)
    #     System.out.println(text)





