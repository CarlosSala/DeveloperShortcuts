package com.example.developershortcut.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.developershortcut.screen.one_screen.OneScreen
import com.example.developershortcut.screen.three_screen.ThreeScreen
import com.example.developershortcut.screen.two_screen.TwoScreen


@Composable
fun AppNavigation(
    navController: NavHostController,
    context: Context,
    paddingValues: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = AppScreens.OneScreen.route
    ) {
        composable(AppScreens.OneScreen.route) {
            OneScreen(context, paddingValues)
        }
        composable(AppScreens.TwoScreen.route) {
            TwoScreen()
        }
        composable(AppScreens.ThreeScreen.route) {
            ThreeScreen()
        }
    }
}