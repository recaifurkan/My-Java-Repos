#include "../include/AppSound.h"
#include <stdio.h>



JNIEXPORT jint JNICALL Java_com_rfbsoft_natives_AppSound_AppSound_calculate
  (JNIEnv * env, jobject object, jint deger2 , jint deger1){
    return deger1 + deger2;
  }

EXAMPLE_DLL int calculate(int a , int b){
  return a+b;
}