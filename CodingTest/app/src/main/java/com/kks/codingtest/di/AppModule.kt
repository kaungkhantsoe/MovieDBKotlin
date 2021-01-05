package com.kks.codingtest.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kks.codingtest.BaseApp
import com.kks.codingtest.R
import com.kks.codingtest.data.api.MovieListApi
import com.kks.codingtest.data.persistance.AppDB
import com.kks.codingtest.repositries.MainRepository
import com.kks.codingtest.util.BASE_URL
import com.kks.codingtest.util.DB_NAME
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppDb(app: Application): AppDB {
        return Room
            .databaseBuilder(app, AppDB::class.java, DB_NAME)
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {

        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .centerCrop()
    }

    @Singleton
    @Provides
    fun provideGlideInstance(
        application: Application?,
        requestOptions: RequestOptions?
    ): RequestManager {
        return Glide.with(application!!)
            .setDefaultRequestOptions(requestOptions!!)
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): MovieListApi = retrofit.create(MovieListApi::class.java)

}