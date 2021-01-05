package com.kks.codingtest.di.main

import android.app.Application
import androidx.room.Room
import com.kks.codingtest.data.api.MovieListApi
import com.kks.codingtest.data.persistance.AppDB
import com.kks.codingtest.data.persistance.MovieDao
import com.kks.codingtest.repositries.MainRepository
import com.kks.codingtest.util.DB_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by kaungkhantsoe on 1/5/21.
 **/
@Module
class MainModule {

    @MainScope
    @Provides
    fun provideAuthTokenDao(db: AppDB): MovieDao{
        return db.movieDao()
    }

    @MainScope
    @Provides
    fun provideMainRepository(movieListApi: MovieListApi, db: AppDB): MainRepository {
        return MainRepository(movieListApi, db)
    }
}