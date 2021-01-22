package com.example.sehatqapplicationtest

import android.app.Application
import com.example.sehatqapplicationtest.util.PreferenceManager
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by M Hafidh Abdul Aziz on 19/01/21.
 */

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PreferenceManager.init(this)
        FacebookSdk.sdkInitialize(applicationContext);
        AppEventsLogger.activateApp(this);

    }
}