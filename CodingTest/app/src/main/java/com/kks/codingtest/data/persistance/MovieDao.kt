package com.kks.codingtest.data.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kks.codingtest.data.models.ResultModel
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(model: List<ResultModel>)

    @Query("SELECT * FROM movie where pageNumber = :pageNumber")
    fun getDataByPageNumber(pageNumber: Int): Single<List<ResultModel>>

    @Query("DELETE FROM movie")
    fun deleteAll()

    @Query("SELECT * FROM movie where pageNumber = :pageNumber")
    fun getAllDataAsFlow(pageNumber: Int): Flow<List<ResultModel>>


}