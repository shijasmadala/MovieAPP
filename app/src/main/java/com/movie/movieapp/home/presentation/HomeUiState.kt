package com.movie.movieapp.home.presentation

import com.movie.movieapp.home.data.dto.MovieResponseDto

sealed class HomeUiState {
    data class Success(val movieData: MovieResponseDto) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
    object Loading : HomeUiState()
    object Empty : HomeUiState()
}