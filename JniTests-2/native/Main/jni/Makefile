jniPath = "E:\Program Files\Java\jdk1.8.0_181\include"
jni-md-path = "E:\Program Files\Java\jdk1.8.0_181\include\win32"
CLASSPATH = "F:\WorkSpaces\Java\JniMingw\src\main\java"
javah = javah  
javahFlags = -classpath $(CLASSPATH)


CC = gcc
CFLAGS = -I $(jniPath) -I $(jni-md-path) -c
BIT_FLAG = -m64

INC_DIR = include
SRC_DIR = src
OBJ_DIR = obj
BUILD_DIR = build

objects = main deneme1 deneme2

# $@ ilk değişken için sol taraftaki yani
# $< sağ taraf için 
# $(patsubst %,$(OBJ_DIR)/%.o,$(objects)) bu ise object gibi çok dosyalı işlemlerde başına veya sonuna kelime eklemek için


%.exe: $(objects)
	$(CC) $(patsubst %,$(OBJ_DIR)/%.o,$(objects)) -o $(BUILD_DIR)/$@

%.dll: $(objects)
	$(CC) $(BIT_FLAG) -Wall -shared $(patsubst %,$(OBJ_DIR)/%.o,$(objects)) -o $(BUILD_DIR)/$@

$(objects) :
	$(CC) $(CFLAGS) $(SRC_DIR)/$@.c -o $(OBJ_DIR)/$@.o 

# create h file from java class
# make class=com.rfbsoft.natives.AppSound.Main Main.h
%.h:
	$(javah) $(javahFlags) -o $(INC_DIR)/$@ $(class) 
  
