#include "Main.h"
#include <iostream>



JNIEXPORT void JNICALL Java_Main_helloWorld
(JNIEnv * env, jclass cls , jstring text) {
	const char *str = env->GetStringUTFChars(text,0);
	printf(str);
}

