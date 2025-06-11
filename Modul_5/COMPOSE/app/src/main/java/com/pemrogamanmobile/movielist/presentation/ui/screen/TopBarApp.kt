package com.pemrogamanmobile.movielist.presentation.ui.screen

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarApp(title: String, backgroundColor: Color = MaterialTheme.colorScheme.primary) {
    Surface(
        color = Color(0xFF131418),
    ) {
        TopAppBar(
            title = {
                Text(text = title, color = Color.White)
            },
            scrollBehavior = null, // Optional, remove if not needed
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = backgroundColor
            )
        )
    }
}