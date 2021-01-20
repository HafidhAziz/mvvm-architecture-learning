package com.example.sehatqapplicationtest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by M Hafidh Abdul Aziz on 19/01/21.
 */

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}