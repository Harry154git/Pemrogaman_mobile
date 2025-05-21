package com.pemrogamanmobile.myfavoritegames

import android.app.Application
import timber.log.Timber
import com.pemrogamanmobile.myfavoritegames.BuildConfig

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Inisialisasi Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}