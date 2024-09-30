package com.example.developershortcut.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.developershortcut.screen.fourscreen.TabRowManagerScreen
import com.example.developershortcut.screen.onescreen.ShortcutsScreen
import com.example.developershortcut.screen.threescreen.NoteScreen
import com.example.developershortcut.screen.twoscreen.IntentActionsScreen


@Composable
fun AppNavigation(
    navController: NavHostController,
    context: Context,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = AppScreens.OneScreen.route
    ) {
        composable(AppScreens.OneScreen.route) {
            ShortcutsScreen(
                context = context,
                modifier = modifier
            )
        }
        composable(AppScreens.TwoScreen.route) {
            IntentActionsScreen(
                context = context,
                modifier = modifier
            )
        }
        composable(AppScreens.ThreeScreen.route) {
            NoteScreen(paddingValues)
        }
        composable(AppScreens.FourScreen.route) {
            TabRowManagerScreen(paddingValues)
        }
        /*     composable(AppScreens.FourScreen.route) {
                 val viewModel: SystemInfoViewModel = viewModel(
                     factory = SystemInfoViewModelFactory(LocalContext.current)
                 )
                 SystemInfoScreen(paddingValues)
             }*/
    }
}