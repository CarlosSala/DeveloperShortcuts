package com.example.developershortcut.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.developershortcut.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarMain(drawerState: DrawerState, openDrawer: (DrawerState) -> Unit) {

    val scope = rememberCoroutineScope()

    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.drawer_menu))
        },
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