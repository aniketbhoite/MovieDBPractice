package com.aniket.moviedbpractice.repositories

import com.aniket.moviedbpractice.network.MovieApiClient
import com.aniket.moviedbpractice.responses.base.BaseError
import com.aniket.moviedbpractice.responses.base.Result
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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

    suspend fun parseErrorResponse(errorString: String): Result.Error {
        val jsonAdapter: JsonAdapter<BaseError> =
            MovieApiClient.moshi.adapter<BaseError>(BaseError::class.java)
        return withContext(Dispatchers.IO) {
            val error = jsonAdapter.fromJson(errorString)
            Result.Error(error!!.statusMessage)
        }
    }

}