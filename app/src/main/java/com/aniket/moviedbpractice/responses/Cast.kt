package com.aniket.moviedbpractice.responses

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Cast(
    @Json(name = "cast_id") val castId: Int,

    val name: String,

    val character: String,

    @Json(name = "profile_path") val profilePath: String?

)