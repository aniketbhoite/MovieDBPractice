package com.aniket.moviedbpractice.responses

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
class Genre(
    val id: Int,

    val name: String
)