package com.example.developershortcut.screen.twoscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.developershortcut.navigation.IntentActionsScreenSettings


@Composable
fun IntentActionScreenSettings(intentActionModelId: Int) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,

    ) {
        Text(text = "hola $intentActionModelId")
    }
}

@Preview
@Composable
fun IntentActionsScreenSettingsPreview() {
    IntentActionsScreenSettings(1)
}