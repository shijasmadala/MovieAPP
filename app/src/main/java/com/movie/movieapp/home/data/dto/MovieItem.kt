package com.movie.movieapp.home.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class MovieItem(
    @Json(name = "desc")
    val desc: String?,
    @Json(name = "genre")
    val genre: List<String?>?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "posterurl")
    val posterurl: String?,
    @Json(name = "rating")
    val rating: String?,
    @Json(name = "release")
    val release: String?,
    @Json(name = "title")
    val title: String?
) : Parcelable