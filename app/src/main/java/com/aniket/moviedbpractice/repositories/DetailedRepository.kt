package com.aniket.moviedbpractice.repositories

import com.aniket.moviedbpractice.network.ApiServices
import com.aniket.moviedbpractice.responses.Credits
import com.aniket.moviedbpractice.responses.MovieSynopsis
import com.aniket.moviedbpractice.responses.ReviewsResponse
import com.aniket.moviedbpractice.responses.base.BaseError
import com.aniket.moviedbpractice.responses.base.Result
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailedRepository(private val apiServices: ApiServices) : BaseRepository() {

    val moshi: Moshi by lazy {
        Moshi.Builder().build()
    }

    suspend fun getSynopsis(movieId: Int): Result<MovieSynopsis> {
        return safeApiCall("Something went wrong",
            call = {
                val response = apiServices.getMovieSynopsis(movieId)
                if (response.isSuccessful) {
                    if (response.body() != null)
                        Result.Success(response.body()!!)
                    else
                        Result.Error("No Data found")
                } else {
                    val jsonAdapter: JsonAdapter<BaseError> =
                        moshi.adapter<BaseError>(BaseError::class.java)
                    withContext(Dispatchers.IO) {
                        val error = jsonAdapter.fromJson(response.errorBody()!!.toString())
                        Result.Error(error!!.statusMessage)
                    }
                }
            })
    }


    suspend fun getCredits(movieId: Int): Result<Credits> {
        return safeApiCall("Something went wrong",
            call = {
                val response = apiServices.getCredits(movieId)
                if (response.isSuccessful) {
                    if (response.body() != null)
                        Result.Success(response.body()!!)
                    else
                        Result.Error("No Data found")
                } else {
                    val jsonAdapter: JsonAdapter<BaseError> =
                        moshi.adapter<BaseError>(BaseError::class.java)
                    withContext(Dispatchers.IO) {
                        val error = jsonAdapter.fromJson(response.errorBody()!!.toString())
                        Result.Error(error!!.statusMessage)
                    }
                }
            })
    }


    suspend fun getReviews(movieId: Int): Result<ReviewsResponse> {
        return safeApiCall("Something went wrong",
            call = {
                val response = apiServices.getReviews(movieId)
                if (response.isSuccessful) {
                    if (response.body() != null)
                        Result.Success(response.body()!!)
                    else
                        Result.Error("No Data found")
                } else {
                    val jsonAdapter: JsonAdapter<BaseError> =
                        moshi.adapter<BaseError>(BaseError::class.java)
                    withContext(Dispatchers.IO) {
                        val error = jsonAdapter.fromJson(response.errorBody()!!.toString())
                        Result.Error(error!!.statusMessage)
                    }
                }

            })
    }


}