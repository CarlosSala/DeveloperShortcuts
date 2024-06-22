package com.example.developershortcut.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.developershortcut.screen.onescreen.ShortcutsScreen
import com.example.developershortcut.screen.fourscreen.SystemInfoScreen
import com.example.developershortcut.screen.threescreen.NotesScreen
import com.example.developershortcut.screen.twoscreen.IntentActionsScreen


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
            ShortcutsScreen(context, paddingValues)
        }
        composable(AppScreens.TwoScreen.route) {
            IntentActionsScreen(context, paddingValues)
        }
        composable(AppScreens.ThreeScreen.route) {
            NotesScreen()
        }
        composable(AppScreens.FourScreen.route) {
/*            *//*val viewModel: SystemInfoViewModel = viewModel(
                factory = SystemInfoViewModelFactory(LocalContext.current)
            )
*/
            SystemInfoScreen(paddingValues)
        }
    }
}