package com.kks.codingtest.di.main_coroutine

import androidx.lifecycle.ViewModel
import com.kks.codingtest.di.ViewModelKey
import com.kks.codingtest.ui.main_coroutine.MainCoroutineViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by kaungkhantsoe on 1/14/21.
 **/
@Module
internal abstract class MainCoroutineViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainCoroutineViewModel::class)
    abstract fun bindMainCoroutineViewModel(viewModel: MainCoroutineViewModel): ViewModel

}