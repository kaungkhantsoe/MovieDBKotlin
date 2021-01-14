package com.kks.codingtest.ui.main_coroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kks.codingtest.common.DataState
import com.kks.codingtest.repositries.MainCoroutineRepository
import com.kks.codingtest.repositries.MainRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by kaungkhantsoe on 1/13/21.
 **/
class MainCoroutineViewModel
@Inject constructor(val mainRepository: MainCoroutineRepository) : ViewModel() {

    var apiKey = ""

    var page = 0

    fun getMovieListFrom(page: Int) {
        this.page = page

        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            mainRepository.liveData.value = DataState.Error(throwable)
            throwable.printStackTrace()
        }

        viewModelScope.launch(handler) {
            println("ViewModel Thread name ${Thread.currentThread().name}")
            mainRepository.fetchDataFromDb(apiKey,page)
        }
    }

}