package com.pemrogamanmobile.movielist.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pemrogamanmobile.movielist.presentation.viewmodel.MovieViewModel
import com.pemrogamanmobile.movielist.utils.NetworkResult

@Composable
fun MainScreen(viewModel: MovieViewModel, navController: NavController) {
    val moviesResult by viewModel.movies.collectAsState()

    Scaffold(topBar = {
        TopBarApp(title = "popular movie", backgroundColor = Color(0xFF131418))
    }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = Color(0xFF131418)
        ) {
            when (moviesResult) {
                is NetworkResult.Loading -> CircularProgressIndicator()
                is NetworkResult.Success -> {
                    val movies = (moviesResult as NetworkResult.Success).data
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(movies) { movie ->
                            MovieCard(movie) {
                                navController.navigate("detail/${movie.id}")
                            }
                        }
                    }
                }
                is NetworkResult.Error -> Text(
                    "Error: ${(moviesResult as NetworkResult.Error).message}",
                    color = Color.White
                )
            }
        }
    }
}