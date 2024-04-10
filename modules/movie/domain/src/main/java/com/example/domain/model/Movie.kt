package com.example.domain.model

data class Movie(
    val id: Int,
    val originalLanguage: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val isAdult: Boolean
)
