package com.kks.codingtest.di.main

import androidx.lifecycle.ViewModel
import com.kks.codingtest.di.ViewModelKey
import com.kks.codingtest.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/
@Module
internal abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

}