package com.example.developershortcut.navigation

import android.graphics.Color.rgb
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.developershortcut.R
import kotlinx.coroutines.launch


@Preview
@Composable
fun TopAppBarMainPreview() {
    TopAppBarMain(
        title = "Title",
        drawerState = DrawerState(DrawerValue.Closed),
        openDrawer = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarMain(
    title: String,
    drawerState: DrawerState,
    openDrawer: (DrawerState) -> Unit
) {

    val scope = rememberCoroutineScope()

    TopAppBar(
        title = {
            // Text(text = stringResource(id = R.string.drawer_menu))
            Text(text = title)
        },
        colors = TopAppBarColors(
            containerColor = Color(rgb(243, 237, 247)),
            scrolledContainerColor = Color.LightGray,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black,
        ),
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    openDrawer(drawerState)
                }
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu Icon")
            }
        },

        actions = {

            AppBarAction(Icons.Default.Search, onClick = {/*TODO*/ })
            AppBarAction(Icons.Default.Share, onClick = {/*TODO*/ })
        }
    )
}

@Composable
private fun AppBarAction(
    imageVector: ImageVector,
    onClick: () -> Unit
) {

    IconButton(onClick = onClick) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
    }
}