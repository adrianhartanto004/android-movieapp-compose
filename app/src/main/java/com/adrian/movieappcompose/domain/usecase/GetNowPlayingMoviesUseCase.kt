package com.adrian.movieappcompose.domain.usecase

import com.adrian.movieappcompose.domain.model.NowPlayingMovies
import kotlinx.coroutines.flow.Flow

interface GetNowPlayingMoviesUseCase {
    operator fun invoke(): Flow<Result<List<NowPlayingMovies>>>
}
