package com.pemrogamanmobile.movielist.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pemrogamanmobile.movielist.presentation.viewmodel.MovieViewModel
import com.pemrogamanmobile.movielist.utils.NetworkResult

@Composable
fun MainScreen(
    viewModel: MovieViewModel,
    navController: NavController,
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit
) {
    val moviesResult by viewModel.movies.collectAsState()
    val colorScheme = MaterialTheme.colorScheme

    Scaffold(
        topBar = {
            TopBarApp(
                title = "Popular Movie",
                isDarkMode = isDarkMode,
                onToggleTheme = onToggleTheme
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = colorScheme.background
        ) {
            when (moviesResult) {
                is NetworkResult.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = colorScheme.primary)
                    }
                }

                is NetworkResult.Success -> {
                    val movies = (moviesResult as NetworkResult.Success).data
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        items(movies) { movie ->
                            MovieCard(movie) {
                                navController.navigate("detail/${movie.id}")
                            }
                        }
                    }
                }

                is NetworkResult.Error -> {
                    Text(
                        text = "Error: ${(moviesResult as NetworkResult.Error).message}",
                        color = colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}