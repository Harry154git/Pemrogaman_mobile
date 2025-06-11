package com.pemrogamanmobile.movielist.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.pemrogamanmobile.movielist.presentation.viewmodel.MovieViewModel
import com.pemrogamanmobile.movielist.utils.NetworkResult

@Composable
fun DetailScreen(
    viewModel: MovieViewModel,
    movieId: Int,
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit
) {
    val moviesResult by viewModel.movies.collectAsState()
    val scrollState = rememberScrollState()
    val colorScheme = MaterialTheme.colorScheme

    Scaffold(
        topBar = {
            TopBarApp(
                title = "Detail Movie",
                isDarkMode = isDarkMode,
                onToggleTheme = onToggleTheme
            )
        }
    ) { paddingValues ->
        when (moviesResult) {
            is NetworkResult.Loading -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = colorScheme.primary)
                }
            }

            is NetworkResult.Success -> {
                val movie = (moviesResult as NetworkResult.Success).data.find { it.id == movieId }
                if (movie != null) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        color = colorScheme.background
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .verticalScroll(scrollState)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.posterPath}"),
                                contentDescription = movie.title,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(400.dp)
                                    .clip(RoundedCornerShape(6.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = movie.title,
                                        style = MaterialTheme.typography.headlineSmall.copy(
                                            color = colorScheme.onBackground
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )
                                    Text(
                                        text = movie.releaseDate,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = colorScheme.onBackground.copy(alpha = 0.6f)
                                        ),
                                        modifier = Modifier.align(Alignment.CenterVertically)
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Tentang film ini:",
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontSize = 14.sp,
                                        color = colorScheme.onBackground,
                                        fontWeight = FontWeight.Bold
                                    )
                                )

                                Text(
                                    text = movie.overview,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontSize = 14.sp,
                                        color = colorScheme.onBackground
                                    ),
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }
                } else {
                    Text(
                        text = "Movie not found",
                        color = colorScheme.error,
                        modifier = Modifier
                            .padding(paddingValues)
                            .padding(16.dp)
                    )
                }
            }

            is NetworkResult.Error -> {
                Text(
                    text = "Error loading movie",
                    color = colorScheme.error,
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(16.dp)
                )
            }
        }
    }
}