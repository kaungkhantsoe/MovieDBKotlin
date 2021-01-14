package com.kks.codingtest.util

/**
 * Created by kaungkhantsoe on 1/13/21.
 **/
class SharedKeys {

    init {
        System.loadLibrary("libnative-lib");
    }

    external fun getApiKey(): String?

}