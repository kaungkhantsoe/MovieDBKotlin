package com.kks.codingtest.repositries

import androidx.lifecycle.MutableLiveData
import com.kks.codingtest.common.DataState
import com.kks.codingtest.data.api.MovieListApi
import com.kks.codingtest.data.models.MovieListModel
import com.kks.codingtest.data.models.ResultModel
import com.kks.codingtest.data.persistance.AppDB
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.lang.Exception
import java.lang.Runnable
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/

class MainRepository
@Inject
constructor(
    val movieListApi: MovieListApi,
    val appDB: AppDB
) {

    private val _liveData by lazy { MutableLiveData<DataState<List<ResultModel>>>() }

    val liveData: MutableLiveData<DataState<List<ResultModel>>>
        get() = _liveData

    var apiKey = ""
    var page = 1

    private fun insertData(): Disposable {
        return movieListApi.getMovieList(apiKey, page)
            .flatMap {
                val tempList = mutableListOf<ResultModel>()
                for (model in it.results) {
                    model.pageNumber = page
                    tempList.add(model)
                }

                return@flatMap Flowable.just(
                    MovieListModel(
                        it.page,
                        tempList
                    )
                )
            }
            .subscribeOn(Schedulers.io())
            .subscribeWith(subscribeToDatabase())
    }

    private fun subscribeToDatabase(): DisposableSubscriber<MovieListModel> {
        return object : DisposableSubscriber<MovieListModel>() {

            override fun onNext(movielist: MovieListModel?) {

                if (movielist != null) {
                    val entityList = movielist.results.toList()
                    val executor: Executor = Executors.newSingleThreadExecutor()
                    executor.execute(Runnable {
                        if (entityList[0].pageNumber == 1)
                            appDB.movieDao().deleteAll()

                        appDB.movieDao().insertData(entityList)
                    })
                }
            }

            override fun onError(t: Throwable?) {
                if (t != null)
                    _liveData.postValue(DataState.Error(t))
                Timber.e(t)
            }

            override fun onComplete() {
                getMoviesFromDb()
            }
        }
    }

    private fun getMoviesFromDb(): Disposable {
        _liveData.postValue(DataState.Loading)
        return appDB.movieDao()
            .getDataByPageNumber(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { movieList ->
                    if (movieList != null && movieList.isNotEmpty()) {
                        _liveData.postValue(DataState.Success(movieList))
                    } else {
                        insertData()
                    }
                },
                {
                    if (it.localizedMessage != null)
                        _liveData.postValue(DataState.Error(it))
                }
            )
    }

    fun fetchDataFromDatabase(apiKey: String, page: Int): Disposable {
        this.apiKey = apiKey
        this.page = page
        return getMoviesFromDb()
    }
}