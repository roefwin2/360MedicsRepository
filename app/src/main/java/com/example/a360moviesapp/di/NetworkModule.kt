package com.example.a360moviesapp.di

import com.example.a360moviesapp.data.api.MoviesApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
        moshi : Moshi
    ): MoviesApi {
        val retrofit =
            Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(MoshiConverterFactory.create(moshi).withNullSerialization())
                .build()
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    fun providesMoshi() : Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
}