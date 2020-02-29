package com.aniket.moviedbpractice.responses

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class MoviesListResponse(
    val page: Int,

    val results: Array<MovieData>,

    @Json(name = "total_pages") val totalPages: Int,

    @Json(name = "total_results") val totalResults: Int

)
