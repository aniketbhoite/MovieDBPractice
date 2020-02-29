package com.aniket.moviedbpractice.network

import com.aniket.moviedbpractice.responses.Credits
import com.aniket.moviedbpractice.responses.MovieSynopsis
import com.aniket.moviedbpractice.responses.NowPlayingMoviesResponse
import com.aniket.moviedbpractice.responses.ReviewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {


    @GET("movie/now_playing")
    suspend fun getNowPlaying(): Response<NowPlayingMoviesResponse>

    @GET("movie/{movieId}/reviews")
    suspend fun getReviews(@Path("movieId") movieId: Int): Response<ReviewsResponse>

    @GET("movie/{movieId}")
    suspend fun getMovieSynopsis(@Path("movieId") movieId: Int): Response<MovieSynopsis>

    @GET("movie/{movieId}/credits")
    suspend fun getCredits(@Path("movieId") movieId: Int): Response<Credits>
}