package com.kks.codingtest.common

/**
 * Created by kaungkhantsoe on 1/4/21.
 **/
sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: String) : DataState<Nothing>()
    object Loading: DataState<Nothing>()
}
