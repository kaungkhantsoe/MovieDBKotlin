package com.kks.codingtest.di.home

import androidx.lifecycle.ViewModel
import com.kks.codingtest.di.ViewModelKey
import com.kks.codingtest.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by kaungkhantsoe on 1/14/21.
 **/
@Module
abstract class HomeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel) : ViewModel
}