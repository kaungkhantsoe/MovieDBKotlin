package com.kks.codingtest.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.kks.codingtest.data.persistance.AppDB
import com.kks.codingtest.repositries.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by kaungkhantsoe on 1/5/21.
 **/
@Module
class RoomModule {

//    @Singleton
//    @Provides
//    fun provideAppDatabase(context: Application): AppDB = AppDB.getInstance(context)!!
//
//
//    @Singleton
//    @Provides
//    fun provideRepository(): MainRepository = MainRepository()
}