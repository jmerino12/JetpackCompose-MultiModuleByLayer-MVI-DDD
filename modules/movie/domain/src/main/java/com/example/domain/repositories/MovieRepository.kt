package com.example.domain.repositories

import com.example.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>
    fun getMovie(id: Int): Flow<Movie?>
}