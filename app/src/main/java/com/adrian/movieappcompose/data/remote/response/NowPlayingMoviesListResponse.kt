package com.adrian.movieappcompose.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlayingMoviesListResponse(
    val page: Int,
    @SerialName("results")
    val nowPlayingMoviesResponse: List<NowPlayingMoviesResponse>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)