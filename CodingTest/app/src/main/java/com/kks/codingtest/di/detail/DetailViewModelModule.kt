package com.kks.codingtest.di.detail

import androidx.lifecycle.ViewModel
import com.kks.codingtest.di.ViewModelKey
import com.kks.codingtest.ui.detail.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class DetailViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel
}