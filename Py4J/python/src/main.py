"""
Created on Dec 19, 2009

@author: Recai Furkan Bostancı
"""
from py4j.java_gateway import JavaGateway,GatewayParameters


gateway = JavaGateway(gateway_parameters=GatewayParameters(auto_field=True))
# gateway = JavaGateway()


object = gateway.entry_point.getObject()

jvm = gateway.jvm

System = jvm.java.lang.System
Scanner = jvm.java.util.Scanner
String = jvm.java.lang.String
Main = jvm.com.byrfb.Main
Http = jvm.com.byrfb.Http


def inputPOutputj():
    global text
    while 1:
        text = input("Değer gir")
        print(text)
        System.out.println(text)


def inputJOutputJ():
    global text
    while 1:
        sc = Scanner(Main.getSystemIn());
        text = sc.nextLine()
        print(text)
        System.out.println(text)


def sendHttpGet():
    USER_AGENT = String("Mozilla/5.0")
    url = String("https://www.facebook.com")
    http = Http(url)
    http.sendGet("")


if __name__ == "__main__":
    sendHttpGet()

    inputPOutputj()

    # inputJOutputJ()





