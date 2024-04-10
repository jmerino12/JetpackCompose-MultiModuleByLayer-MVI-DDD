package com.example.infrastructure.httpclient.service

import com.example.infrastructure.httpclient.dto.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "4005b57c0bfee0310d6958d0c8683128"

interface MovieService {
    @GET("/3/movie/upcoming")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): MoviesDto

}