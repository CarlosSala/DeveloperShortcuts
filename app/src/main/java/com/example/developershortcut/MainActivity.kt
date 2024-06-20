package com.example.developershortcut

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.developershortcut.navigation.AppNavigation
import com.example.developershortcut.navigation.AppScreens
import com.example.developershortcut.navigation.BottomNavigationBar
import com.example.developershortcut.navigation.SplashNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            SplashNavigation()
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val navController = rememberNavController()
    var selectedScreen by remember { mutableStateOf<AppScreens>(AppScreens.OneScreen) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(selectedScreen) { screen ->
                selectedScreen = screen

                navController.navigate(screen.route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    ) { paddingValues ->

        AppNavigation(navController, context, paddingValues)
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}