package com.example.developershortcut.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.developershortcut.screen.fourscreen.TabRowManagerScreen
import com.example.developershortcut.screen.shortcutsscreen.ShortcutsScreen
import com.example.developershortcut.screen.notesscreen.NotesScreen
import com.example.developershortcut.screen.intentactionsscreen.IntentActionScreenSettings
import com.example.developershortcut.screen.intentactionsscreen.IntentActionsScreen


@Composable
fun AppNavigation(
    navController: NavHostController,
    context: Context,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    onTitle: (String) -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = AppScreens.OneScreen.route
    ) {
        composable(AppScreens.OneScreen.route) {
            ShortcutsScreen(
                context = context,
                modifier = modifier
            ) { title ->
                onTitle(title)
            }
        }
        composable(AppScreens.IntentActionsScreen.route) {
            IntentActionsScreen(
                context = context,
                modifier = modifier,
                onIntentActionCustom = { item ->
                    navController.navigate(IntentActionsScreenSettings(intentActionId = item.id))
                }, onTitle = { title ->
                    onTitle(title)
                }
            )
        }
        composable<IntentActionsScreenSettings> { backStackEntry ->
            val intentActionId = backStackEntry.toRoute<IntentActionsScreenSettings>()
            IntentActionScreenSettings(intentActionModelId = intentActionId.intentActionId)
        }

        composable(AppScreens.ThreeScreen.route) {
            NotesScreen(modifier) { title ->
                onTitle(title)
            }
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