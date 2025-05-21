package com.pemrogamanmobile.myfavoritegames

import android.app.Application
import timber.log.Timber

class MyFavoriteGamesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}