package com.pemrogamanmobile.myfavoritegames

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pemrogamanmobile.myfavoritegames.model.Games

@Composable
fun HomeScreen(
    gamesViewModel: GamesViewModel,
    onDetailClick: (Games) -> Unit
) {
    val games by gamesViewModel.gamesState.collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(games) { game ->
            GameCard(
                games = game,
                onDetailClick = onDetailClick
            )
        }
    }
}
