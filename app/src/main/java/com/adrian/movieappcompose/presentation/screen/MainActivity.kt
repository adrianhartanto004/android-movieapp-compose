package com.adrian.movieappcompose.presentation.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.adrian.movieappcompose.presentation.screen.home.HomeScreen
import com.adrian.movieappcompose.presentation.screen.home.HomeScreenEvent
import com.adrian.movieappcompose.presentation.screen.home.HomeViewModel
import com.adrian.movieappcompose.presentation.screen.theme.MovieAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppComposeTheme {
                MainScreen()
            }
        }
    }
}
