package com.example.a360moviesapp.domain.repository

import com.example.a360moviesapp.data.models.NetworkMovie
import com.example.a360moviesapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MovieRepository {
    fun getMovie(title: String) : Flow<Resource<List<NetworkMovie>>>
}