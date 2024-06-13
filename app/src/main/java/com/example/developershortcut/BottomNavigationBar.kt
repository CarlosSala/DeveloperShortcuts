package com.example.developershortcut

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.developershortcut.navigation.AppScreens


@Composable
fun BottomNavigationBar(navController: NavController) {

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            label = { Text("Shortcuts") },
            selected = false,
            onClick = { navController.navigate(AppScreens.TestOneScreen.route) })

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_android_24),
                    contentDescription = null
                )
            },
            label = { Text("Actions") },
            selected = false,
            onClick = { navController.navigate(AppScreens.TestTwoScreen.route) })

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_miscellaneous_services_24),
                    contentDescription = null
                )
            },
            label = { Text("Others") },
            selected = false,
            onClick = { navController.navigate(AppScreens.TestThreeScreen.route) })

    }
}