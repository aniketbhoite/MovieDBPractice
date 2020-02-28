package com.aniket.moviedbpractice.responses

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class NowPlayingMoviesResponse(
    val page: Int,

    val result: Array<MovieData>,

    @Json(name = "total_pages") val totalPages: Int
)
