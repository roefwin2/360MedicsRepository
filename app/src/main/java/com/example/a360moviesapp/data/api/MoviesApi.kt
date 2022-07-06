package com.example.a360moviesapp.data.api

import retrofit2.http.GET

interface MoviesApi {

    @GET
    suspend fun getMovies() : List<NetworkMovie>
}