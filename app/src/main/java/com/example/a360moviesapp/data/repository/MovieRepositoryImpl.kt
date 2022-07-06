package com.example.a360moviesapp.data.repository

import com.example.a360moviesapp.data.api.MoviesApi
import com.example.a360moviesapp.data.models.NetworkMovie
import com.example.a360moviesapp.domain.repository.MovieRepository
import com.example.a360moviesapp.utils.Error
import com.example.a360moviesapp.utils.Loading
import com.example.a360moviesapp.utils.Resource
import com.example.a360moviesapp.utils.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MoviesApi
) : MovieRepository {
    override fun getMovie(title: String): Flow<Resource<NetworkMovie>> = flow {
        emit(Loading(null))
        try {
            val result = api.getMovies(title)
            emit(Success(result))
        } catch (h: HttpException) {
            emit(Error<NetworkMovie>(h.toString()))
        } catch (e: IOException) {
            emit(Error<NetworkMovie>(e.toString()))
        } catch (d: Exception) {
            emit(Error<NetworkMovie>(d.toString()))
        }
    }
}