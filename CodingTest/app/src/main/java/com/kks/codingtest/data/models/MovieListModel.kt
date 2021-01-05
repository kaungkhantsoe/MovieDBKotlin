package com.kks.codingtest.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.kks.codingtest.common.Pageable
import kotlinx.serialization.*
import kotlinx.serialization.internal.*
import java.io.Serializable

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/

class MovieListModel (
    val page: Long,
    val results: List<Result>
)

@Entity(tableName = "movie")
class Result (
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

//    @SerialName("genre_ids")
//    val genreIDS: List<Long>,

    var pageNumber: Int,

    @PrimaryKey
    @SerializedName("id")
    val id: Long,

//    @SerialName("original_language")
//    val originalLanguage: OriginalLanguage,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("title")
    val title: String?,

//    @SerializedName(video)
//    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Long
): Pageable, Serializable


//class ListOfLongTypeConverter {
//    @TypeConverter
//    fun listToJson(value: List<Long>?) = Gson().toJson(value)
//
//    @TypeConverter
//    fun jsonToList(value: String) = Gson().fromJson(value, Array<Long>::class.java).toList()
//}
