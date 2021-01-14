package com.kks.codingtest.data.api

import com.kks.codingtest.data.models.MovieListModel
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/
interface MovieListApi {

    @GET("movie/now_playing")
    fun getMovieList(@Query("api_key") api_key: String, @Query("page") page: Int): Flowable<MovieListModel>

    @GET("movie/now_playing")
    suspend fun getMovieListAsFlow(@Query("api_key") api_key: String, @Query("page") page: Int): MovieListModel

}