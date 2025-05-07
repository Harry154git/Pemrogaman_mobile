package com.pemrogamanmobile.myfavoritegames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pemrogamanmobile.myfavoritegames.ui.theme.MyFavoriteGamesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFavoriteGamesTheme {
                Surface(color = Color(0xFF131418)) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(onDetailClick = { affirmation ->
                                navController.navigate(
                                    "detail/${affirmation.titleResourceId}/${affirmation.imageResourceId}/${affirmation.yearResourceId}/${affirmation.detailResourceId}"
                                )
                            })
                        }

                        composable(
                            route = "detail/{titleResId}/{imageResId}/{yearResId}/{detailResId}",
                            arguments = listOf(
                                navArgument("titleResId") { type = NavType.IntType },
                                navArgument("imageResId") { type = NavType.IntType },
                                navArgument("yearResId") { type = NavType.IntType },
                                navArgument("detailResId") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            val titleResId = backStackEntry.arguments?.getInt("titleResId") ?: 0
                            val imageResId = backStackEntry.arguments?.getInt("imageResId") ?: 0
                            val yearResId = backStackEntry.arguments?.getInt("yearResId") ?: 0
                            val detailResId = backStackEntry.arguments?.getInt("detailResId") ?: 0

                            DetailScreen(
                                titleResId = titleResId,
                                imageResId = imageResId,
                                yearResId = yearResId,
                                detailResId = detailResId
                            )
                        }
                    }
                }
            }
        }
    }
}