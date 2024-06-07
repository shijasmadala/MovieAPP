package com.movie.movieapp.di

import com.movie.movieapp.home.data.repository.HomeRepositoryImpl
import com.movie.movieapp.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideHomeRepository(homeRepositoryImpl: HomeRepositoryImpl) : HomeRepository
}