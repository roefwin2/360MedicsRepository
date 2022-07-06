package com.example.a360moviesapp.di

import com.example.a360moviesapp.data.api.MoviesApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideMovieApi(
    ): MoviesApi {
        val retrofit =
            Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        return retrofit.create(MoviesApi::class.java)
    }
}