package com.example.a360moviesapp.di

import com.example.a360moviesapp.data.repository.MovieRepositoryImpl
import com.example.a360moviesapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl) : MovieRepository
}