#include "../include/deneme1.h"
#include "../include/deneme2.h"
#include "../include/main.h"
#include <stdio.h>
/*
 * Class:     com_rfbsoft_natives_AppSound_Main
 * Method:    topla
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_rfbsoft_natives_AppSound_Main_topla
  (JNIEnv * env, jobject obje, jint var1, jint var2){
      return topla(var1,var2);

  }

/*
 * Class:     com_rfbsoft_natives_AppSound_Main
 * Method:    carp
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_rfbsoft_natives_AppSound_Main_carp
  (JNIEnv * env, jobject obje, jint var1, jint var2){
      return carp(var1,var2);
  }

void main(){
    printf("Çalışıyor");
}