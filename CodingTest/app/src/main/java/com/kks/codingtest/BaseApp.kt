package com.kks.codingtest

import android.content.Context
import com.kks.codingtest.custom_control.AndroidCommonSetup
import com.kks.codingtest.di.DaggerAppComponent
import com.kks.codingtest.util.ReleaseTree
import dagger.android.DaggerApplication
import timber.log.Timber

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/
class BaseApp : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()

        AndroidCommonSetup.getInstance().init(applicationContext)

        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(
                        "Class:%s: Line: %s, Method: %s",
                        super.createStackElementTag(element),
                        element.lineNumber,
                        element.methodName
                    )
                }
            })
        } else {
            Timber.plant(ReleaseTree())
        }
    }

}