package com.adrian.movieappcompose.data.remote.service

import com.adrian.movieappcompose.data.remote.response.NowPlayingMoviesListResponse
import com.adrian.movieappcompose.data.remote.retrofit.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAppService {
    @GET("movie/now_playing")
    suspend fun fetchNowPlayingMovies(
        @Query("page") page: Int
    ): ApiResult<NowPlayingMoviesListResponse>
}
