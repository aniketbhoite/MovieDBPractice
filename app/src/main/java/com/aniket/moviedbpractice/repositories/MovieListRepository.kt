package com.aniket.moviedbpractice.repositories

import com.aniket.moviedbpractice.network.ApiServices
import com.aniket.moviedbpractice.responses.NowPlayingMoviesResponse
import com.aniket.moviedbpractice.responses.base.BaseError
import com.aniket.moviedbpractice.responses.base.Result
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieListRepository(private val apiServices: ApiServices) : BaseRepository() {

    //TODO: take moshi in constructer
    val moshi: Moshi by lazy {
        Moshi.Builder().build()
    }

    suspend fun getNowPlayingMovies(): Result<NowPlayingMoviesResponse> {

        return safeApiCall("Something went wrong",
            call = {
                val response = apiServices.getNowPlaying()
                if (response.isSuccessful) {
                    if (response.body() != null)
                        Result.Success(response.body()!!)
                    else {
                        Result.Error("No Data found")
                    }
                } else {
                    val jsonAdapter: JsonAdapter<BaseError> =
                        moshi.adapter<BaseError>(BaseError::class.java)
                    withContext(Dispatchers.IO) {
                        val error = jsonAdapter.fromJson(response.errorBody()!!.toString())
                        Result.Error(error!!.statusMessage)
                    }
                }
            }
        )

    }


}