package com.pemrogamanmobile.myfavoritegames

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.pemrogamanmobile.myfavoritegames.data.Datasource
import com.pemrogamanmobile.myfavoritegames.model.Games

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onDetailClick: (Games) -> Unit
) {
    val games = Datasource().loadGames()

    LazyColumn(modifier = modifier) {
        items(games) { game ->
            GameCard(games = game, onDetailClick = onDetailClick)
        }
    }
}