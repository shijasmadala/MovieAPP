package com.movie.movieapp.home.data.source

import com.movie.movieapp.home.data.dto.MovieResponseDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface HomeService {
    @GET("dummy.json")
    suspend fun getMovieData(): ApiResponse<MovieResponseDto>
}