package com.adrian.movieappcompose.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adrian.movieappcompose.domain.model.NowPlayingMovies

@Entity(tableName = "nowPlayingMovies")
data class NowPlayingMoviesEntity(
    @PrimaryKey
    val id: Int,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)

fun NowPlayingMoviesEntity.toDomainModel() =
    NowPlayingMovies(
        id,
        posterPath,
        releaseDate,
        title,
        voteAverage,
        voteCount
    )