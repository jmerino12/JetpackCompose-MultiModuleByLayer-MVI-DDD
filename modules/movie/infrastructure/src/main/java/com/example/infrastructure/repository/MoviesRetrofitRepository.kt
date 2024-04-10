package com.example.infrastructure.repository

import com.example.domain.model.Movie
import com.example.infrastructure.anticorruption.MovieTranslate
import com.example.infrastructure.httpclient.service.MovieService
import com.example.infrastructure.repository.contracts.MovieRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRetrofitRepository @Inject constructor(private val movieService: MovieService) :
    MovieRemoteRepository {
    override fun getMovies(): Flow<List<Movie>> {
        return flow { emit(movieService.getMovies().results.map { MovieTranslate.fromDtoToDomain(it) }) }
    }

}