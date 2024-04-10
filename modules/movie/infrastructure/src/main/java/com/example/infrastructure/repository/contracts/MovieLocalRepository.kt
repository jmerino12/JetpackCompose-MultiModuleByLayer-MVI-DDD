package com.example.infrastructure.repository.contracts

import com.example.domain.model.Movie
import com.example.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

interface MovieLocalRepository : MovieRepository {
    fun isEmpty(): Flow<Boolean>
    suspend fun insertMovies(movies: List<Movie>)
}