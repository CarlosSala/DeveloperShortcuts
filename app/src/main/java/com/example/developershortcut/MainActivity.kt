package com.example.developershortcut

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.developershortcut.navigation.AppNavigation
import com.example.developershortcut.navigation.AppScreens
import com.example.developershortcut.navigation.BottomNavigationBar
import com.example.developershortcut.navigation.SplashNavigation
import com.example.developershortcut.navigation.TopAppBarMain
import kotlinx.coroutines.launch

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

    val drawerItem = listOf(
        DrawerItems(Icons.Default.Face, "Profile", 0, false),
        DrawerItems(Icons.Filled.Email, "Inbox", 32, true),
        DrawerItems(Icons.Filled.Favorite, "Favorite", 32, true),
        DrawerItems(Icons.Filled.Settings, "Setting", 0, false)
    )

    val drawerItem2 = listOf(
        DrawerItems(Icons.Default.Share, "Share", 0, false),
        DrawerItems(Icons.Filled.Star, "Rate", 0, false)
    )

    var selectedItem by remember { mutableStateOf(drawerItem[0]) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(drawerContent = {

        ModalDrawerSheet {
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color(0xffffc107)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        Modifier.wrapContentSize(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "profile pic",
                            modifier = Modifier
                                .size(130.dp)
                                .clip(CircleShape)
                        )
                        Text(
                            text = "Mr Shrek",
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    HorizontalDivider(
                        Modifier.align(Alignment.BottomCenter), thickness = 1.dp,
                        color = Color.Gray
                    )
                }
                drawerItem.forEach {
                    NavigationDrawerItem(label = { Text(text = it.text) },
                        selected = it == selectedItem,
                        onClick = {
                            selectedItem = it

                            scope.launch {
                                drawerState.close()
                            }

                        },
                        modifier = Modifier.padding(horizontal = 16.dp),
                        icon = {
                            Icon(imageVector = it.icon, contentDescription = it.text)
                        },
                        badge = {
                            if (it.hasBadge) {
                                BadgedBox(badge = {
                                    Badge {
                                        Text(text = it.badgeCount.toString(), fontSize = 17.sp)
                                    }
                                }) {

                                }
                            }
                        }
                    )
                }
                HorizontalDivider(
                    Modifier.align(Alignment.CenterHorizontally), thickness = 1.dp,
                    color = Color.Gray
                )
                drawerItem2.forEach {
                    NavigationDrawerItem(label = { Text(text = it.text) },
                        selected = it == selectedItem,
                        onClick = {
                            selectedItem = it

                            scope.launch {
                                drawerState.close()
                            }

                        },
                        modifier = Modifier.padding(horizontal = 16.dp),
                        icon = {
                            Icon(imageVector = it.icon, contentDescription = it.text)
                        }
                    )
                }
            }
        }
    }, drawerState = drawerState) {

        Scaffold(
            topBar = {
                TopAppBarMain(drawerState) {
                    scope.launch {
                        it.open()
                    }
                }
            },
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
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

data class DrawerItems(

    val icon: ImageVector,
    val text: String,
    val badgeCount: Int,
    val hasBadge: Boolean
)