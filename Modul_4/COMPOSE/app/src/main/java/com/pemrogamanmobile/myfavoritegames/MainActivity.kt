package com.pemrogamanmobile.myfavoritegames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pemrogamanmobile.myfavoritegames.data.Datasource
import com.pemrogamanmobile.myfavoritegames.ui.theme.MyFavoriteGamesTheme
import androidx.compose.runtime.LaunchedEffect
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Log saat MainActivity dibuat
        Timber.d("MainActivity created")

        setContent {
            MyFavoriteGamesTheme {
                Surface(color = Color(0xFF131418)) {
                    val navController = rememberNavController()

                    // menggunakan parameter String "All"
                    val gamesViewModel: GamesViewModel = viewModel(
                        factory = GamesViewModelFactory(
                            gameCategory = "All",
                            datasource = Datasource()
                        )
                    )

                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            Timber.d("Navigated to Home Screen")

                            HomeScreen(
                                gamesViewModel = gamesViewModel,
                                onDetailClick = { game ->
                                    gamesViewModel.onGameClicked(game) // set selectedGame di ViewModel

                                    // Log saat tombol Detail ditekan
                                    Timber.d("Detail button clicked untuk game: $game")

                                    navController.navigate("detail")
                                }
                            )
                        }

                        composable("detail") {
                            Timber.d("Navigated to Detail Screen")

                            val selectedGame by gamesViewModel.selectedGame.collectAsState()

                            selectedGame?.let { game ->
                                // Log data game yang dipilih
                                Timber.d("Displaying details for game: $game")

                                DetailScreen(
                                    titleResId = game.titleResourceId,
                                    imageResId = game.imageResourceId,
                                    yearResId = game.yearResourceId,
                                    detailResId = game.detailResourceId
                                )
                            } ?: run {
                                // Kalau tidak ada selectedGame, navigasi balik ke home
                                Timber.d("No selected game found, navigating back to Home Screen")
                                LaunchedEffect(Unit) {
                                    navController.popBackStack()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
