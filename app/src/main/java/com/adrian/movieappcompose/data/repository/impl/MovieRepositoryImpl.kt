package com.adrian.movieappcompose.data.repository.impl

import com.adrian.movieappcompose.data.local.dao.NowPlayingMoviesDao
import com.adrian.movieappcompose.data.local.entity.toDomainModel
import com.adrian.movieappcompose.data.remote.response.toEntityModel
import com.adrian.movieappcompose.data.remote.retrofit.ApiResult
import com.adrian.movieappcompose.data.remote.service.MovieAppService
import com.adrian.movieappcompose.data.repository.MovieRepository
import com.adrian.movieappcompose.domain.model.NowPlayingMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieAppService: MovieAppService,
    private val nowPlayingMoviesDao: NowPlayingMoviesDao
): MovieRepository {

    override fun fetchNowPlayingMovies(): Flow<Result<Unit>> = flow {
        when (val apiResult = movieAppService.fetchNowPlayingMovies(1)) {
            is ApiResult.Success -> {
                apiResult
                    .data
                    .nowPlayingMoviesResponse
                    .also { movies ->
                        val entityModels = movies.map { it.toEntityModel() }
                        nowPlayingMoviesDao.deleteAll()
                        nowPlayingMoviesDao.insertAll(entityModels)
                    }

                emit(Result.success(Unit))
            }
            is ApiResult.Error -> {
                emit(Result.failure(Throwable()))
            }
            is ApiResult.Exception -> {
                emit(Result.failure(apiResult.throwable))
            }
        }
    }

    override fun getNowPlayingMovies(): Flow<Result<List<NowPlayingMovies>>> = flow {
        val movies = nowPlayingMoviesDao.fetchAll().map {
            it.toDomainModel()
        }
        emit(Result.success(movies))
    }.catch { e ->
        emit(Result.failure(e))
    }
}
