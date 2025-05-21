package com.pemrogamanmobile.myfavoritegames

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pemrogamanmobile.myfavoritegames.data.Datasource

class GamesViewModelFactory(
    private val gameCategory: String,
    private val datasource: Datasource = Datasource()
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GamesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GamesViewModel(gameCategory, datasource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}