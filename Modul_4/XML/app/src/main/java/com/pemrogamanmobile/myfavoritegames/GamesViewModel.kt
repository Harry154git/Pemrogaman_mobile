package com.pemrogamanmobile.myfavoritegames

import androidx.lifecycle.ViewModel
import com.pemrogamanmobile.myfavoritegames.data.DataSource
import com.pemrogamanmobile.myfavoritegames.data.Games
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber

class GamesViewModel(
    private val gameCategory: String
) : ViewModel() {

    // StateFlow untuk menyimpan daftar game
    private val _gamesState = MutableStateFlow(
        DataSource.loadGames().filter { it.category == gameCategory }
    )
    val gamesState: StateFlow<List<Games>> get() = _gamesState

    // StateFlow untuk menyimpan game yang dipilih
    private val _selectedGame = MutableStateFlow<Games?>(null)
    val selectedGame: StateFlow<Games?> get() = _selectedGame

    init {
        // Log data game yang di-load
        Timber.d("Games data loaded: ${_gamesState.value}")
    }

    // Fungsi untuk menangani klik pada item game
    fun onGameClicked(game: Games) {
        _selectedGame.update { game }
        Timber.d("Game selected: $game")
    }
}

class GamesViewModelFactory(
    private val gameCategory: String
) : androidx.lifecycle.ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GamesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GamesViewModel(gameCategory) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}