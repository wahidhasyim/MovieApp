package com.wahidhasyim.movieapp.base

import com.wahidhasyim.movieapp.data.network.exception.NoInternetException
import com.wahidhasyim.movieapp.data.network.exception.NotFoundException
import com.wahidhasyim.movieapp.data.network.exception.UnAuthorizedException
import com.wahidhasyim.movieapp.data.network.exception.UnknownException
import com.wahidhasyim.movieapp.data.network.response.DataResult
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import retrofit2.HttpException
import retrofit2.Response

open class BaseService {

    protected suspend fun <T : Any> createCall(call: suspend () -> Response<T>): DataResult<T> {

        val response: Response<T>
        try {
            response = call.invoke()
        } catch (t: Throwable) {
            t.printStackTrace()
            return DataResult.Error(mapToNetworkError(t))
        }

        if (response.isSuccessful) {
            if (response.body() != null) {
                return DataResult.Success(response.body()!!)
            }
        } else {
            val errorBody = response.errorBody()
            return if (errorBody != null) {
                DataResult.Error(mapApiException(response.code()))
            } else DataResult.Error(mapApiException(0))
        }
        return DataResult.Error(HttpException(response))
    }

    private fun mapApiException(code: Int): Exception {
        return when (code) {
            HttpURLConnection.HTTP_NOT_FOUND -> NotFoundException()
            HttpURLConnection.HTTP_UNAUTHORIZED -> UnAuthorizedException()
            else -> UnknownException()
        }
    }

    private fun mapToNetworkError(t: Throwable): Exception {
        return when (t) {
            is SocketTimeoutException
            -> SocketTimeoutException("Connection Timed Out")

            is UnknownHostException
            -> NoInternetException()

            else
            -> UnknownException()

        }
    }
}