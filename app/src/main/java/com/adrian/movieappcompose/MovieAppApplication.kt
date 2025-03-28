package com.adrian.movieappcompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieAppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}