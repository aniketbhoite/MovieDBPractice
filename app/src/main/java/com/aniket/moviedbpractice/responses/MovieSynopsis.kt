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

    val overview: String?,

    val genres: Array<Genre>,

    @Json(name = "vote_average") val voteAverage: Float,

    val runtime: Int
) {
    val runtimeString: String
        get() = formatRuntime(runtime)

    private fun formatRuntime(time: Int): String {
        val hours: Int = time / 60 //since both are ints, you get an int

        val minutes: Int = time % 60

        return "${hours}h ${minutes}min"
    }

    val genersString: String
        get() = formatGenres(genres)


    private fun formatGenres(genres: Array<Genre>): String {
        return genres.joinToString(" | ") {
            it.name
        }
    }
}