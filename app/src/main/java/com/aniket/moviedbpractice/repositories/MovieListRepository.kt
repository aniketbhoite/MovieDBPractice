package com.aniket.moviedbpractice.repositories

import com.aniket.moviedbpractice.network.ApiServices
import com.aniket.moviedbpractice.responses.MoviesListResponse
import com.aniket.moviedbpractice.responses.base.Result

class MovieListRepository(private val apiServices: ApiServices) : BaseRepository() {

    //TODO: take moshi in constructer


    suspend fun getNowPlayingMovies(): Result<MoviesListResponse> {

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
                    parseErrorResponse(response.errorBody()!!.toString())
                }
            }
        )

    }


}