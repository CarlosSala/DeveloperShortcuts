package com.example.developershortcut.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.developershortcut.screen.test_one_screen.TestOneScreen
import com.example.developershortcut.screen.test_three_screen.TestThreeScreen
import com.example.developershortcut.screen.test_two_screen.TestTwoScreen


@Composable
fun AppNavigation(
    navController: NavHostController,
    context: Context,
    paddingValues: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = AppScreens.TestOneScreen.route
    ) {
        composable(AppScreens.TestOneScreen.route) {
            TestOneScreen(context, paddingValues)
        }
        composable(AppScreens.TestTwoScreen.route) {
            TestTwoScreen()
        }
        composable(AppScreens.TestThreeScreen.route) {
            TestThreeScreen()
        }
    }
}