package com.example.infrastructure.repository.contracts

import com.example.domain.model.Movie
import kotlinx.coroutines.flow.Flow

fun interface MovieRemoteRepository {
    fun getMovies(): Flow<List<Movie>>
}