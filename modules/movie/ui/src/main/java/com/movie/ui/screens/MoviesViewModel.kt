package com.movie.ui.screens

import androidx.lifecycle.viewModelScope
import com.example.domain.services.MovieService
import com.user.infrastructure.repositories.contracts.UserLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieService: MovieService,
    private val userLocalRepository: UserLocalRepository
) :
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
            try {

                val user = userLocalRepository.getUser().first()
                val movies = movieService.getMovies(user.getAge()).first()

                setState { copy(movies = movies, isLoading = false, isError = false) }
            } catch (e: Exception) {

                setState { copy(isLoading = false, isError = true) }
            }
        }
    }
}
