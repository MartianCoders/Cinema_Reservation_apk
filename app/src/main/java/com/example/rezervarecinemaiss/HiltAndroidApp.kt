package com.example.rezervarecinemaiss

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltAndroidApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}