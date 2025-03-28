package com.adrian.movieappcompose.presentation.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.adrian.movieappcompose.R
import com.adrian.movieappcompose.data.Constants

@Composable
fun HomeScreen(viewModel: HomeViewModel, onMovieClick: (Int) -> Unit) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    viewModel.onEvent(HomeScreenEvent.LoadNowPlayingMovies)
    viewModel.onEvent(HomeScreenEvent.RefreshNowPlayingMovies)

    NowPlayingMovies(uiState, onMovieClick)
}

@Composable
fun NowPlayingMovies(uiState: HomeScreenState, onMovieClick: (Int) -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        items(items = uiState.nowPlayingMovies, itemContent = { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onMovieClick(item.id) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Constants.BASE_IMAGE_URL + item.posterPath)
                        .crossfade(true)
                        .placeholder(R.drawable.image_placeholder_1)
                        .error(R.drawable.image_placeholder_2)
                        .build(),
                    contentDescription = "Now Playing Movie cover",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(screenWidth / 2.3f)
                        .height(screenHeight / 3f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(item.title)
                    Text(item.voteAverage.toString())
                }
            }
        })
    }
}
