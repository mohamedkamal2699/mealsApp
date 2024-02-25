package com.example.domain.common


sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val msg: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
