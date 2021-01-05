package com.kks.codingtest.di

import com.kks.codingtest.di.detail.DetailScope
import com.kks.codingtest.di.detail.DetailViewModelModule
import com.kks.codingtest.di.main.MainModule
import com.kks.codingtest.di.main.MainScope
import com.kks.codingtest.di.modules.MainViewModelModule
import com.kks.codingtest.ui.detail.DetailActivity
import com.kks.codingtest.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/

@Module
internal abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(modules = [
        MainViewModelModule::class,
        MainModule::class
    ])
    internal abstract fun contributeMainActivity(): MainActivity

    @DetailScope
    @ContributesAndroidInjector(modules = [
        DetailViewModelModule::class,
    ])
    internal abstract fun contributeDetailActivity(): DetailActivity

}