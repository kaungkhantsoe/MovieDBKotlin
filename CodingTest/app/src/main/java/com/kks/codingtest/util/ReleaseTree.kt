package com.kks.codingtest.util

import android.util.Log
import timber.log.Timber

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/
class ReleaseTree : @org.jetbrains.annotations.NotNull Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR || priority == Log.WARN){
            //SEND ERROR REPORTS TO Crashlytics.
        }
    }
}