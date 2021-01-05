package com.kks.codingtest.ui.main

import androidx.lifecycle.ViewModel
import com.kks.codingtest.repositries.MainRepository
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/
class MainViewModel @Inject constructor( val mainRepository: MainRepository) : ViewModel() {

    var apiKey = ""

    private val disposable by lazy { CompositeDisposable() }

    var page = 1

    fun getData(page: Int) {
        this.page = page
        disposable.add(mainRepository.fetchDataFromDatabase(apiKey,page))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}