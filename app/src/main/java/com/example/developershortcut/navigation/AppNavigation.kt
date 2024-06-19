package com.example.developershortcut.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.developershortcut.screen.onescreen.OneScreen
import com.example.developershortcut.screen.threescreen.ThreeScreen
import com.example.developershortcut.screen.twoscreen.TwoScreen


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
            TwoScreen(context, paddingValues)
        }
        composable(AppScreens.ThreeScreen.route) {
            ThreeScreen()
        }
    }
}