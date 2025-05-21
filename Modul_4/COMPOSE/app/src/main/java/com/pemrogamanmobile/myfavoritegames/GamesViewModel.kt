package com.pemrogamanmobile.myfavoritegames

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.pemrogamanmobile.myfavoritegames.data.Datasource
import com.pemrogamanmobile.myfavoritegames.model.Games
import timber.log.Timber

class GamesViewModel(
    private val gameCategory: String,
    private val datasource: Datasource
) : ViewModel() {

    private val _gamesState = MutableStateFlow(
        datasource.loadGames().filter { it.category == gameCategory }
    )
    val gamesState: StateFlow<List<Games>> get() = _gamesState

    private val _selectedGame = MutableStateFlow<Games?>(null)
    val selectedGame: StateFlow<Games?> get() = _selectedGame

    init {
        // Log saat data masuk ke list
        Timber.d("Games data loaded: ${_gamesState.value}")
    }

    fun onGameClicked(game: Games) {
        _selectedGame.value = game
        // Log data game yang dipilih
        Timber.d("Game selected: $game")
    }
}