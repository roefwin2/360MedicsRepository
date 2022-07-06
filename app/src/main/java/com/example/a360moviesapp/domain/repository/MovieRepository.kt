package com.example.a360moviesapp.domain.repository

import com.example.a360moviesapp.domain.models.Movie
import com.example.a360moviesapp.utils.Resource
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    fun getMovie(title: String) : Flow<Resource<Movie>>
}