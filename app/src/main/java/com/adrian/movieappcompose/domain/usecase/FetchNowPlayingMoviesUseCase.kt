package com.adrian.movieappcompose.domain.usecase

import kotlinx.coroutines.flow.Flow

interface FetchNowPlayingMoviesUseCase {
    operator fun invoke(): Flow<Result<Unit>>
}