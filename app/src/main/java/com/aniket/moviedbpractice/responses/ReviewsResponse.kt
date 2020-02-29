package com.aniket.moviedbpractice.responses

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class ReviewsResponse(
    val id: Int,

    @Json(name = "total_results") val totalResults: Int,

    val page: Int,

    @Json(name = "total_pages") val totalPages: Int,

    val results: Array<Review>
)