package com.pemrogamanmobile.movielist.data.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("movie_prefs", Context.MODE_PRIVATE)

    fun saveThemeSetting(isDarkMode: Boolean) {
        prefs.edit().putBoolean("dark_mode", isDarkMode).apply()
    }

    fun isDarkMode(): Boolean = prefs.getBoolean("dark_mode", false)
}