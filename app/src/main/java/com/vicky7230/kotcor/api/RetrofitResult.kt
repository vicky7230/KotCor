package com.vicky7230.kotcor.api

sealed class RetrofitResult<out T: Any> {
    data class Success<out T : Any>(val data: T) : RetrofitResult<T>()
    data class Error(val exception: Exception) : RetrofitResult<Nothing>()
}