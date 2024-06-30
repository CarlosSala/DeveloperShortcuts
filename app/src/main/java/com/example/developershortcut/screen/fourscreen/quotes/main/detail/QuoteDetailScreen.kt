package com.example.developershortcut.screen.fourscreen.quotes.main.detail

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun QuoteDetailScreen(id: Int) {

    Text(text = "hola $id")

    /*    val quote = remember {
            // getMedia().first { it.id == mediaId }
        }

        Scaffold(
            // topBar = { TopAppBar(title = { Text(text = mediaItem.title) }) }
        ) { padding ->

            // Thumb(mediaItem = mediaItem, Modifier.padding(padding))
        }*/

}