#include <stdio.h>
#include <stdlib.h>
#include "com_github_jni_demo_TestJNI.h"
JNIEXPORT jstring JNICALL Java_com_github_jni_1demo_TestJNI_HelloWord
        (JNIEnv *env, jobject, jstring str) {
    return str;
}