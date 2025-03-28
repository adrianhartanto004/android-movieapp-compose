package com.adrian.movieappcompose.domain.model

data class NowPlayingMovies(
    val id: Int,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)