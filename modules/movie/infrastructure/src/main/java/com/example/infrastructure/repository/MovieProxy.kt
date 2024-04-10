package com.example.infrastructure.repository

import com.example.domain.model.Movie
import com.example.domain.repositories.MovieRepository
import com.example.infrastructure.NetworkVerify
import com.example.infrastructure.repository.contracts.MovieLocalRepository
import com.example.infrastructure.repository.contracts.MovieRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MovieProxy @Inject constructor(
    private val movieLocalRepository: MovieLocalRepository,
    private val movieRemoteRepository: MovieRemoteRepository,
    private val networkVerify: NetworkVerify
) : MovieRepository {
    override fun getMovies(): Flow<List<Movie>> {
        return if (networkVerify.isNetworkConnection()) {
            movieRemoteRepository.getMovies().onEach {
                insertMovies(movies = it)
            }
        } else {
            movieLocalRepository.getMovies()
        }
    }

    override fun getMovie(id: Int): Flow<Movie?> {
        return movieLocalRepository.getMovie(id)
    }

    private suspend fun insertMovies(movies: List<Movie>) {
        movieLocalRepository.insertMovies(movies)
    }
}