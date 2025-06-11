package com.pemrogamanmobile.movielist.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.pemrogamanmobile.movielist.presentation.viewmodel.MovieViewModel
import com.pemrogamanmobile.movielist.utils.NetworkResult

@Composable
fun DetailScreen(viewModel: MovieViewModel, movieId: Int) {
    val moviesResult by viewModel.movies.collectAsState()

    val scrollState = rememberScrollState()

    Scaffold(topBar = {
        TopBarApp(title = "detail movie", backgroundColor = Color(0xFF131418))
    }) { it ->
        when (moviesResult) {
            is NetworkResult.Loading -> CircularProgressIndicator(modifier = Modifier.padding(it))
            is NetworkResult.Success -> {
                val movie = (moviesResult as NetworkResult.Success).data.find { it.id == movieId }
                if (movie != null) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color(0xFF131418)
                    ) {
                        Column(
                            modifier = Modifier.padding(it).padding(16.dp)
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
                                        style = TextStyle(
                                            fontSize = 26.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )
                                    Text(
                                        text = movie.releaseDate,
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            color = Color(0xFF7C7C86)
                                        ),
                                        modifier = Modifier.align(Alignment.CenterVertically)
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "tentang film ini:",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                )

                                Text(
                                    text = movie.overview,
                                    style = TextStyle(fontSize = 14.sp, color = Color.White),
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }
                } else {
                    Text("Movie not found", modifier = Modifier.padding(it).padding(16.dp))
                }
            }
            is NetworkResult.Error -> Text(
                "Error loading movie",
                modifier = Modifier.padding(it).padding(16.dp)
            )
        }
    }
}