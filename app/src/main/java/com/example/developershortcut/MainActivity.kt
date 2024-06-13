package com.example.developershortcut

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.developershortcut.model.ActionModel
import com.example.developershortcut.model.actions


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_settings_24),
                            contentDescription = "Developer Options"
                        )
                    },
                    label = { Text("Developer Options") },
                    selected = false,
                    onClick = {
                        //val intent = Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS")
                        val intent =
                            Intent(android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS)
                        startActivity(context, intent, null)
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_settings_24),
                            contentDescription = "About Phone"
                        )
                    },
                    label = { Text("About Phone") },
                    selected = false,
                    onClick = {
                        //val intent = Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS")
                        val intent = Intent(android.provider.Settings.ACTION_DEVICE_INFO_SETTINGS)
                        startActivity(context, intent, null)
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_settings_24),
                            contentDescription = "About Phone"
                        )
                    },
                    label = { Text("About Phone") },
                    selected = false,
                    onClick = {
                        val intent = Intent(android.provider.Settings.ACTION_SETTINGS)
                        intent.setClassName(
                            "com.miui.system.overlay",
                            "com.miui.permcenter.permissions.AppPermissionsEditorActivity"
                        )
                        intent.addCategory(Intent.CATEGORY_DEFAULT)
                        startActivity(context, intent, null)
                    }
                )
            }
        }
    ) { paddingValues ->
        // Your screen content goes here, for example:
        // Column(modifier = Modifier.padding(innerPadding)) {

        LazyVerticalGrid(
            contentPadding = paddingValues,
            // columns = GridCells.Fixed(2)
            columns = GridCells.Adaptive(150.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)

        ) {

            items(actions()) { item ->


                MyActions(context, item)
            }


        }

        // Add other composables as needed

    }
}

@Composable
fun MyActions(context: Context, actionModel: ActionModel) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {

            IconButton(modifier = Modifier.fillMaxWidth(),
                onClick = { myNavigation(context, actionModel.route ) }) {
                Icon(
                    painter = painterResource(id = actionModel.icon),
                    contentDescription = "Favorite Icon",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(48.dp)
                )
            }
            Text(
                text = actionModel.title,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )

        }
    }

}

fun myNavigation(context: Context, route: String) {

    val intent = Intent(route)
    startActivity(context, intent, null)
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
