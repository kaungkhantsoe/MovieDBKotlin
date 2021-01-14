package com.kks.codingtest.data.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kks.codingtest.data.models.ResultModel

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/
@Database(
    entities = [ResultModel::class],
    exportSchema = false,
    version = 9
)
//@TypeConverters(ListOfLongTypeConverter::class)
abstract class AppDB : RoomDatabase() {
    // define dao
    abstract fun movieDao(): MovieDao

//    companion object {
//        const val DB_NAME = "TodoDatabase"
//
//        @Volatile
//        private lateinit var INSTANCE: AppDB
//
//        fun getInstance(context: Context): AppDB {
//            synchronized(this) {
//                if (!::INSTANCE.isInitialized)
//                    INSTANCE = Room.databaseBuilder(context, AppDB::class.java, DB_NAME)
//                        .fallbackToDestructiveMigration() // In a state of migration, re-construct DB. Loose data.
//                        .build()
//
//                return INSTANCE
//            }
//        }
//    }

}
