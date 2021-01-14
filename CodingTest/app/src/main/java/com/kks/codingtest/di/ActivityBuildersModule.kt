package com.kks.codingtest.di

import com.kks.codingtest.di.detail.DetailScope
import com.kks.codingtest.di.detail.DetailViewModelModule
import com.kks.codingtest.di.home.HomeScope
import com.kks.codingtest.di.home.HomeViewModelModule
import com.kks.codingtest.di.main.MainModule
import com.kks.codingtest.di.main.MainScope
import com.kks.codingtest.di.main.MainViewModelModule
import com.kks.codingtest.di.main_coroutine.MainCoroutineModule
import com.kks.codingtest.di.main_coroutine.MainCoroutineScope
import com.kks.codingtest.di.main_coroutine.MainCoroutineViewModelModule
import com.kks.codingtest.ui.detail.DetailActivity
import com.kks.codingtest.ui.home.HomeActivity
import com.kks.codingtest.ui.main.MainActivity
import com.kks.codingtest.ui.main_coroutine.MainCoroutineActivity
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

    @MainCoroutineScope
    @ContributesAndroidInjector(modules = [
        MainCoroutineModule::class,
    MainCoroutineViewModelModule::class
    ])
    internal abstract fun contributeMainCoroutineActivity(): MainCoroutineActivity

    @HomeScope
    @ContributesAndroidInjector(modules = [
        HomeViewModelModule::class
    ])
    internal abstract fun contributeHomeActivity(): HomeActivity

}