package com.adrian.movieappcompose.data.remote.response

import com.adrian.movieappcompose.data.local.entity.NowPlayingMoviesEntity
import com.adrian.movieappcompose.domain.model.NowPlayingMovies
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlayingMoviesResponse(
    val id: Int,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String,
    val title: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)

fun NowPlayingMoviesResponse.toEntityModel() =
    NowPlayingMoviesEntity(
        id,
        posterPath,
        releaseDate,
        title,
        voteAverage,
        voteCount
    )

fun NowPlayingMoviesResponse.toDomainModel() =
    NowPlayingMovies(
        id,
        posterPath,
        releaseDate,
        title,
        voteAverage,
        voteCount
    )
