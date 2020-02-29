package com.aniket.moviedbpractice.responses

data class MovieDetailsData(
    val movieSynopsis: MovieSynopsis,

    val credits: Credits?,

    val reviewsResponse: ReviewsResponse?
)