package com.movie.ui.screens

import com.example.domain.model.Movie

class MoviesContract {
    sealed class Event : ViewEvent {

    }

    data class State(
        val movies: List<Movie>,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {

        sealed class Navigation : Effect() {
            data object Back : Navigation()
        }
    }
}