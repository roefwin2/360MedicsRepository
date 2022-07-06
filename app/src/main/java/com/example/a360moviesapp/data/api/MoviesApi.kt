package com.example.a360moviesapp.data.api

import com.example.a360moviesapp.data.models.NetworkMovie
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET(".")
    suspend fun getMovies(@Query("t") title : String) : NetworkMovie
}