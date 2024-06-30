package com.example.developershortcut.screen.fourscreen.quotes.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.developershortcut.navigation.AppScreens
import com.example.developershortcut.screen.fourscreen.quotes.main.QuotesScreen
import com.example.developershortcut.screen.fourscreen.quotes.main.detail.QuoteDetailScreen


@Composable
fun QuoteNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.QuotesScreen.route
    ) {
        composable(AppScreens.QuotesScreen.route) {
            QuotesScreen(navController)
        }
        composable(
            // optional argument
            // route = "detail?mediaId={mediaId}",

            route = AppScreens.QuoteDetailScreenWithArgs.route,
            arguments = listOf(
                navArgument("mediaId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("mediaId")
            requireNotNull(id)
            QuoteDetailScreen(id)
        }
    }
}