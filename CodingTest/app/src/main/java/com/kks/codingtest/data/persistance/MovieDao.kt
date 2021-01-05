package com.kks.codingtest.data.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kks.codingtest.data.models.Result
import io.reactivex.Single

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(model: List<Result>)

    @Query("SELECT * FROM movie where pageNumber = :pageNumber")
    fun getDataByPageNumber(pageNumber: Int): Single<List<Result>>

    @Query("DELETE FROM movie")
    fun deleteAll()

}