package com.aniket.moviedbpractice.responses.base

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: BaseError) : Result<Nothing>()
}