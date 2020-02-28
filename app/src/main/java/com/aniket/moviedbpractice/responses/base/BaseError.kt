package com.aniket.moviedbpractice.responses.base

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class BaseError(
    @Json(name = "status_message") val statusMessage: String,

    val success: Boolean = false,

    @Json(name = "status_code") val statusCode: String
)