package com.kks.codingtest.di

import android.app.Application
import com.kks.codingtest.BaseApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by kaungkhantsoe on 1/4/21.
 **/

@Singleton
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        ActivityBuildersModule::class,
        AppModule::class
    )
)
interface AppComponent: AndroidInjector<BaseApp> {

    /*We declare this builder because we want to push object (Application)
      into the dependency graph at runtime.*/
    @Component.Builder
    interface Builder {

        /*Here be bind the application instance, this is a part
          of the builder now, pushed to the dependency graph*/
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}