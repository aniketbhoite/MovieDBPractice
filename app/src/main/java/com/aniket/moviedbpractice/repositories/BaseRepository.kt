package com.aniket.moviedbpractice.repositories

import com.aniket.moviedbpractice.responses.base.Result

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(
        errorMessage: String? = null,
        call: suspend () -> Result<T>
    ): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            // An exception was thrown when calling the API so we're converting this to an IOException
            Result.Error(errorMessage ?: "")
        }
    }

}