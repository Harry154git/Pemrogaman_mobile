package com.pemrogamanmobile.myfavoritegames

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.pemrogamanmobile.myfavoritegames.data.Datasource
import com.pemrogamanmobile.myfavoritegames.model.Affirmation

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onDetailClick: (Affirmation) -> Unit
) {
    val affirmations = Datasource().loadAffirmations()

    LazyColumn(modifier = modifier) {
        items(affirmations) { affirmation ->
            AffirmationCard(affirmation = affirmation, onDetailClick = onDetailClick)
        }
    }
}