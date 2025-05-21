package com.pemrogamanmobile.myfavoritegames

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GamesViewModelFactory(
    private val gameCategory: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GamesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GamesViewModel(gameCategory) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}