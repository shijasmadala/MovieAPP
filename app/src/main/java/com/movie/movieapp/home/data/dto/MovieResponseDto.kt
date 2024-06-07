package com.movie.movieapp.home.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class MovieResponseDto(
    @Json(name = "homeData")
    val homeData: List<HomeData?>?,
    @Json(name = "title")
    val title: String?
) : Parcelable