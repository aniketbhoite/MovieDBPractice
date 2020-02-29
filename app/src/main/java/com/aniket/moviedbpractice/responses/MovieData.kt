package com.aniket.moviedbpractice.responses

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class MovieData(
    val id: Int,

    @Json(name = "original_title") val title: String,

    @Json(name = "poster_path") val posterLink: String?,

    @Json(name = "release_date") val releaseDate: String
)