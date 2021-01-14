//
// Created by kaungkhantsoe on 1/4/21.
//

#include <jni.h>
#include <string>

//std::string getData(int x) {
//    std::string app_secret = "Null";
//
//    if (x == 1) app_secret = "9a327fe2ad719f692e33ddefd210c47e";
//
//    return app_secret;
//}

std::string getData() {
    std::string app_secret = "Null";

    app_secret = "9a327fe2ad719f692e33ddefd210c47e";

    return app_secret;
}

extern "C"
JNIEXPORT jstring

//JNICALL
//Java_com_kks_codingtest_ui_MainActivity_getApiKey( //path cannot have "_" in names
//        JNIEnv *env,
//        jobject /* this */,
//        jint x) {
//    std::string app_secret = "Null";
//    app_secret = getData(x);
//    return env->NewStringUTF(app_secret.c_str());
//}

JNICALL
Java_com_kks_codingtest_util_SharedKeys_getApiKey( //path cannot have "_" in names
        JNIEnv *env,
        jobject /* this */) {
    std::string app_secret = "Null";
    app_secret = getData();
    return env->NewStringUTF(app_secret.c_str());
}
