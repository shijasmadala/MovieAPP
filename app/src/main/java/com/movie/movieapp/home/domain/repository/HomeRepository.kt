package com.movie.movieapp.home.domain.repository

import com.movie.movieapp.utils.Resource
import com.movie.movieapp.home.data.dto.MovieResponseDto
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getMovieData() : Flow<Resource<MovieResponseDto>>
}