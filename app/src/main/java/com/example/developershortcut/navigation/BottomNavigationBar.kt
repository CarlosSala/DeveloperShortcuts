package com.example.developershortcut.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.developershortcut.R


@Composable
fun BottomNavigationBar(
    // navController: NavController,
    selectedScreen: AppScreens,
    onItemSelected: (AppScreens) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = {
                //Icon(Icons.Filled.Home, contentDescription = null)
                Icon(
                    painter = painterResource(id = R.drawable.ic_android_24),
                    contentDescription = null
                )
            },
            label = { Text("Shortcuts") },
            selected = selectedScreen == AppScreens.OneScreen,
            onClick = { onItemSelected(AppScreens.OneScreen) }
            // onClick = { navController.navigate(AppScreens.OneScreen.route) }
        )
        NavigationBarItem(
            icon = {
                Icon(Icons.Filled.Home, contentDescription = null)
            },
            label = { Text("Actions") },
            selected = selectedScreen == AppScreens.TwoScreen,
            onClick = { onItemSelected(AppScreens.TwoScreen) }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_miscellaneous_services_24),
                    contentDescription = null
                )
            },
            label = { Text("Others") },
            selected = selectedScreen == AppScreens.ThreeScreen,
            onClick = { onItemSelected(AppScreens.ThreeScreen) }
        )
    }
}