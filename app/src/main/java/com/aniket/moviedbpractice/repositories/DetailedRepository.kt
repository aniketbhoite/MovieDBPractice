package com.aniket.moviedbpractice.repositories

import com.aniket.moviedbpractice.network.ApiServices
import com.aniket.moviedbpractice.responses.Credits
import com.aniket.moviedbpractice.responses.MovieSynopsis
import com.aniket.moviedbpractice.responses.MoviesListResponse
import com.aniket.moviedbpractice.responses.ReviewsResponse
import com.aniket.moviedbpractice.responses.base.Result
import com.squareup.moshi.Moshi

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
                    parseErrorResponse(response.errorBody()!!.toString())
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
                    parseErrorResponse(response.errorBody()!!.toString())
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
                    parseErrorResponse(response.errorBody()!!.toString())
                }

            })
    }

    suspend fun getSimilarMovies(movieId: Int): Result<MoviesListResponse> {
        return safeApiCall("Something went wrong",
            call = {
                val response = apiServices.getSimilarMovies(movieId)
                if (response.isSuccessful) {
                    if (response.body() != null)
                        Result.Success(response.body()!!)
                    else Result.Error("No Data found")
                } else {
                    parseErrorResponse(response.errorBody()!!.toString())
                }
            })
    }


}