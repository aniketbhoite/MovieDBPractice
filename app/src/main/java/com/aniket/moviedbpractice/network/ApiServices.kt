package com.aniket.moviedbpractice.network

import com.aniket.moviedbpractice.responses.Credits
import com.aniket.moviedbpractice.responses.MovieSynopsis
import com.aniket.moviedbpractice.responses.MoviesListResponse
import com.aniket.moviedbpractice.responses.ReviewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {


    @GET("movie/now_playing")
    suspend fun getNowPlaying(): Response<MoviesListResponse>

    @GET("movie/{movieId}/reviews")
    suspend fun getReviews(@Path("movieId") movieId: Int): Response<ReviewsResponse>

    @GET("movie/{movieId}")
    suspend fun getMovieSynopsis(@Path("movieId") movieId: Int): Response<MovieSynopsis>

    @GET("movie/{movieId}/credits")
    suspend fun getCredits(@Path("movieId") movieId: Int): Response<Credits>

    @GET("movie/{movieId}/similar")
    suspend fun getSimilarMovies(@Path("movieId") movieId: Int): Response<MoviesListResponse>
}