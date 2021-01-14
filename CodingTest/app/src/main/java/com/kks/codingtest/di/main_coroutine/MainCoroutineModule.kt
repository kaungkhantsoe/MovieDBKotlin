package com.kks.codingtest.di.main_coroutine

import com.kks.codingtest.data.api.MovieListApi
import com.kks.codingtest.data.persistance.AppDB
import com.kks.codingtest.data.persistance.MovieDao
import com.kks.codingtest.di.main.MainScope
import com.kks.codingtest.repositries.MainCoroutineRepository
import com.kks.codingtest.repositries.MainRepository
import dagger.Module
import dagger.Provides

/**
 * Created by kaungkhantsoe on 1/13/21.
 **/
@Module
class MainCoroutineModule {

    @MainCoroutineScope
    @Provides
    fun provideAuthTokenDao(db: AppDB): MovieDao {
        return db.movieDao()
    }

    @MainCoroutineScope
    @Provides
    fun provideMainCoroutineRepository(movieListApi: MovieListApi, db: AppDB): MainCoroutineRepository {
        return MainCoroutineRepository(movieListApi, db)
    }
}