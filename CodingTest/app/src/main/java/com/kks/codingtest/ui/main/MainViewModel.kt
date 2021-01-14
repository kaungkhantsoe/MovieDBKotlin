package com.kks.codingtest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kks.codingtest.common.DataState
import com.kks.codingtest.repositries.MainRepository
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import com.kks.codingtest.data.models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/
class MainViewModel @Inject constructor( val mainRepository: MainRepository) : ViewModel() {

    var apiKey = ""

    var page = 0

    private val _disposable by lazy { CompositeDisposable() }

    fun getData(page: Int) {
        this.page = page
        _disposable.add(mainRepository.fetchDataFromDatabase(apiKey,page))
    }

    override fun onCleared() {
        super.onCleared()
        _disposable.clear()
    }

}