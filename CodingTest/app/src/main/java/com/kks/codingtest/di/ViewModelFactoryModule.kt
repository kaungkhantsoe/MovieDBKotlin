package com.kks.codingtest.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/
@Module
abstract class ViewModelFactoryModule {

    @Binds
    /*When i'm asking for a ViewModelProvider.Factory you will provide me the AppViewModelFactory*/
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}