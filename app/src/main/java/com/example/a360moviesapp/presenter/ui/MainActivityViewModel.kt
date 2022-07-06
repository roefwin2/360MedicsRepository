package com.example.a360moviesapp.presenter.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a360moviesapp.domain.models.Movie
import com.example.a360moviesapp.domain.repository.MovieRepository
import com.example.a360moviesapp.utils.Loading
import com.example.a360moviesapp.utils.NotStarted
import com.example.a360moviesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    var state : MutableState<MovieScreen>  = mutableStateOf(MovieScreen("",NotStarted<Movie>()))
        private set


    fun changeTitle(searchTitle: String){
        state.value = state.value.copy(searchTitle = searchTitle)
    }
    fun fetchMovie() {
        val currentTitle = state.value.searchTitle
        viewModelScope.launch {
            movieRepository.getMovie(currentTitle).collect {
                state.value = state.value.copy(fetchData = it)
            }
        }
    }

    fun resetState() {
        state.value = MovieScreen("",NotStarted<Movie>())
    }
}

data class MovieScreen(
    val searchTitle : String,
    val fetchData : Resource<Movie>
){
    val isEnable : Boolean get() = searchTitle.isNotEmpty() && fetchData !is Loading<Movie>
}