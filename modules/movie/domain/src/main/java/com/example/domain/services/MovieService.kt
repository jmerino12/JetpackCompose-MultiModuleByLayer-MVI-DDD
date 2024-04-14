package com.example.domain.services

import com.example.domain.model.Movie
import com.example.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieService @Inject constructor(private val movieRepository: MovieRepository) {

    companion object {
        const val AGE_RESTRICTION = 18
    }

    fun getMovies(age: Int): Flow<List<Movie>> {
        return movieRepository.getMovies().map { movies ->
            if (age >= AGE_RESTRICTION) {
                movies
            } else {
                movies.filter { movie -> !movie.isAdult }
            }
        }
    }

    fun getMovie(idMovie: Int): Flow<Movie?> {
        return movieRepository.getMovie(idMovie)
    }
}