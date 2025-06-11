package com.pemrogamanmobile.movielist.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pemrogamanmobile.movielist.data.preferences.PreferencesManager

class ThemeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val prefs = PreferencesManager(context)
        return ThemeViewModel(prefs) as T
    }
}