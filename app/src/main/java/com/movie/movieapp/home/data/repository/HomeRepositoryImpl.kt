package com.movie.movieapp.home.data.repository

import com.movie.movieapp.home.data.dto.MovieResponseDto
import com.movie.movieapp.home.data.source.HomeService
import com.movie.movieapp.home.domain.repository.HomeRepository
import com.movie.movieapp.utils.Constants
import com.movie.movieapp.utils.Resource
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeService: HomeService) :
    HomeRepository {
    override suspend fun getMovieData(): Flow<Resource<MovieResponseDto>> = flow {
        emit(Resource.Loading)
        homeService.getMovieData().suspendOnSuccess {
            val response = this.data
            if (response != null) {
                emit(Resource.Success(response))
            }
        }.suspendOnError {
            try {
                when (this.statusCode) {
                    StatusCode.InternalServerError -> emit(Resource.Error(Constants.SERVER_ERROR))
                    else -> emit(Resource.Error("Unable fetch to movie data"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(Constants.GENERAL_ERROR_MESSAGE))
            }
        }.suspendOnException { emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE)) }
    }
}