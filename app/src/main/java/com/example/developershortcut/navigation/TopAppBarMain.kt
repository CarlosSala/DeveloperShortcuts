package com.example.developershortcut.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.developershortcut.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarMain() {

    TopAppBar(
        title = {
            /*        Row {
                        Text(text = stringResource(id = R.string.app_name))
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = Icons.Default.ElectricBolt,
                            contentDescription = null
                        )
                    }*/
            Text(text = stringResource(id = R.string.app_name))
        },
        navigationIcon = {
            AppBarAction(Icons.Default.Menu, onClick = {/*TODO*/ })
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