package com.aniket.moviedbpractice.responses

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Review(
    val author: String,

    val content: String,

    val id: String
)