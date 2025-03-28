package com.adrian.movieappcompose.data.repository

import com.adrian.movieappcompose.domain.model.NowPlayingMovies
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchNowPlayingMovies(): Flow<Result<Unit>>
    fun getNowPlayingMovies(): Flow<Result<List<NowPlayingMovies>>>
}