jniPath = "E:\Program Files\Java\dcevm-11.0.11+1\include"
jni-md-path = "E:\Program Files\Java\dcevm-11.0.11+1\include\win32"

jniPathLinux = "/usr/lib/jvm/java-11-openjdk-amd64/include"
jni-md-path-linux = "/usr/lib/jvm/java-11-openjdk-amd64/include/linux"

CLASSPATH = "F:\WorkSpaces\Java\SystemScreenSaver\src\main\java"
javah = javah
javahFlags = -classpath $(CLASSPATH)


propertiesLibPath = libs/Properties/include


CFLAGS = -I $(jniPath) -I $(jni-md-path) 
CFLAGSLINUX = -I $(jniPathLinux) -I $(jni-md-path-linux) 
BIT_FLAG = -m64

run:
	java "-Djava.library.path=./library" HelloJNI.java 

compile-windows:
	gcc $(BIT_FLAG) -shared -o hello.dll HelloJNI.c $(CFLAGS) -D jni

compile-ubuntu:
	gcc -fPIC $(CFLAGSLINUX) -shared -o libhello.so HelloJNI.c
