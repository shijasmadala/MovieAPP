package com.movie.movieapp.detail.presentation

import androidx.lifecycle.ViewModel
import com.movie.movieapp.home.domain.repository.HomeRepository
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel(){

}