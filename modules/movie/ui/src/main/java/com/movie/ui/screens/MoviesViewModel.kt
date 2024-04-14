package com.movie.ui.screens

import androidx.lifecycle.viewModelScope
import com.example.domain.services.MovieService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val movieService: MovieService) :
    BaseViewModel<MoviesContract.Event, MoviesContract.State, MoviesContract.Effect>() {

    init {
        getMovies()
    }

    override fun setInitialState(): MoviesContract.State =
        MoviesContract.State(emptyList(), isLoading = true, isError = false)

    override fun handleEvents(event: MoviesContract.Event) {
        when (event) {
            else -> Unit
        }
    }

    private fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieService.getMovies(18).collect {
                setState { copy(movies = it, isLoading = false, isError = false) }
            }
        }
    }
}