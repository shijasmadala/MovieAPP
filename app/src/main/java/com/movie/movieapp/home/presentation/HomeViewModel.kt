package com.movie.movieapp.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.movieapp.home.domain.repository.HomeRepository
import com.movie.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    private val _homeState = MutableStateFlow<HomeUiState>(HomeUiState.Empty)
    val homeState = _homeState.asStateFlow()

    init {
        getMovieData()
    }

    private fun getMovieData() = viewModelScope.launch {
        homeRepository.getMovieData().collectLatest { homeData ->
            when (homeData) {
                is Resource.Empty -> {}
                is Resource.Error -> {
                    _homeState.emit(HomeUiState.Error(homeData.error))
                }

                Resource.Loading -> {
                    _homeState.emit(HomeUiState.Loading)
                }

                is Resource.Success -> {
                    _homeState.emit(HomeUiState.Success(homeData.value))
                }
            }
        }
    }
}