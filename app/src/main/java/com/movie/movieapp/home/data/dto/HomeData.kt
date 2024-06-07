package com.movie.movieapp.home.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class HomeData(
    @Json(name = "genre")
    val genre: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "movieslist")
    val movieslist: List<MovieItem?>?,
    @Json(name = "type")
    val type: Int?
) : Parcelable