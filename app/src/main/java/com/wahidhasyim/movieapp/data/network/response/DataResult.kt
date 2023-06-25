package com.wahidhasyim.movieapp.data.network.response

sealed class DataResult<out T: Any> {

    data class Success<out T: Any>(val data: T) : DataResult<T>()

    data class Error(val error: Exception) : DataResult<Nothing>()
}