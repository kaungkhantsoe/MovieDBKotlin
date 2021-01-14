package com.kks.codingtest.repositries

import androidx.lifecycle.MutableLiveData
import com.kks.codingtest.common.DataState
import com.kks.codingtest.data.api.MovieListApi
import com.kks.codingtest.data.models.MovieListModel
import com.kks.codingtest.data.models.ResultModel
import com.kks.codingtest.data.persistance.AppDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by kaungkhantsoe on 1/13/21.
 **/
class MainCoroutineRepository
@Inject
constructor(
    val movieListApi: MovieListApi,
    val appDB: AppDB
) {

    private val _liveData by lazy { MutableLiveData<DataState<List<ResultModel>>>() }

    val liveData: MutableLiveData<DataState<List<ResultModel>>>
        get() = _liveData

    /*
    Get data using flow
     */
    suspend fun fetchDataFromDb(apiKey: String, page: Int) {
        liveData.value = DataState.Loading
        appDB.movieDao().getAllDataAsFlow(page)
            .flowOn(IO)
            .catch { throwable ->
                liveData.value = DataState.Error(throwable)
                Timber.e(throwable)
            }
            .collect { result ->
                if (result.isEmpty())
                    fetchDataFromNetwork(apiKey, page)
                else
                    liveData.value = DataState.Success(result)
            }
    }

    private suspend fun fetchDataFromNetwork(apiKey: String, page: Int) {
        flowOf(movieListApi.getMovieListAsFlow(apiKey, page))
            .flowOn(IO)
            .catch { throwable ->
                liveData.value = DataState.Error(throwable)
                Timber.e(throwable)
            }
            .map { mapData ->
                val tempList = mutableListOf<ResultModel>()
                for (model in mapData.results) {
                    model.pageNumber = page
                    tempList.add(model)
                }

                println("Thread name on map ${Thread.currentThread().name}")
                withContext(IO) {
                    println("Thread name after changing thread ${Thread.currentThread().name}")
                    appDB.movieDao().insertData(tempList)
                }
                return@map  MovieListModel(
                    mapData.page,
                    tempList
                )
            }
            .collect { collectedData ->
                liveData.value = DataState.Success(collectedData.results)
            }
    }

}