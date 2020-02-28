package com.aniket.moviedbpractice.network

import com.aniket.moviedbpractice.responses.NowPlayingMoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {


    @GET("/movie/now_playing")
    suspend fun getNowPlaying(): Response<NowPlayingMoviesResponse>
}