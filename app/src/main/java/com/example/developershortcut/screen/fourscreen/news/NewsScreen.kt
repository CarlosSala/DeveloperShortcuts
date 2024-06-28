package com.example.developershortcut.screen.fourscreen.news

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NewsScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize(), color = Color.LightGray) {

        Greeting(name = "Screen 1")

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello from the $name!",
        modifier = modifier
    )
}