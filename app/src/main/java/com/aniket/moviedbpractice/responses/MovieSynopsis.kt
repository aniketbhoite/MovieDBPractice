package com.aniket.moviedbpractice.responses

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class MovieSynopsis(

    val adult: Boolean = false,

    @Json(name = "backdrop_path") val backdropPath: String?,

    val id: Int,

    val overView: String?,

    val genres: Array<Genre>,

    @Json(name = "vote_average") val voteAverage: Float,

    @Json(name = "vote_cont") val voteCount: Int
)