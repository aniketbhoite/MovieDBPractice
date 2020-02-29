package com.aniket.moviedbpractice.responses

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Credits(
    val id: Int,

    @Json(name = "cast") val casts: Array<Cast>
) {
    val castsString: String
        get() = formatCastsString(
            casts
        )

    private fun formatCastsString(casts: Array<Cast>): String {
        return casts.joinToString(separator = ", ") {
            it.name
        }
    }
}