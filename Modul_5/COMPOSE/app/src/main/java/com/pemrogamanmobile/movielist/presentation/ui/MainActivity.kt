package com.pemrogamanmobile.movielist.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pemrogamanmobile.movielist.data.local.db.MovieDatabase
import com.pemrogamanmobile.movielist.data.remote.RetrofitInstance
import com.pemrogamanmobile.movielist.data.repository.MovieRepositoryImpl
import com.pemrogamanmobile.movielist.domain.usecase.MovieUseCase
import com.pemrogamanmobile.movielist.presentation.ui.screen.DetailScreen
import com.pemrogamanmobile.movielist.presentation.ui.screen.MainScreen
import com.pemrogamanmobile.movielist.presentation.viewmodel.MovieViewModel
import com.pemrogamanmobile.movielist.presentation.viewmodel.MovieViewModelFactory
import com.pemrogamanmobile.movielist.presentation.ui.theme.MovieListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = MovieDatabase.getDatabase(applicationContext)
        val repository = MovieRepositoryImpl(RetrofitInstance.api, db.movieDao())
        val useCase = MovieUseCase(repository)
        val viewModel = ViewModelProvider(this, MovieViewModelFactory(useCase))[MovieViewModel::class.java]

        setContent {
            MovieListTheme {
                Surface(color = Color(0xFF131418)) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            MainScreen(viewModel, navController)
                        }
                        composable("detail/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })) { backStackEntry ->
                            val id = backStackEntry.arguments?.getInt("id") ?: -1
                            DetailScreen(viewModel = viewModel, movieId = id)
                        }
                    }
                }
            }
        }
    }
}