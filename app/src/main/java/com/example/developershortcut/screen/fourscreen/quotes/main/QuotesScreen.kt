package com.example.developershortcut.screen.fourscreen.quotes.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.developershortcut.screen.fourscreen.quotes.domain.model.Movie

@Composable
fun QuotesScreen() {

    val viewModel: QuoteViewModel = viewModel()

    // Solicita las películas populares al iniciar la composición
    LaunchedEffect(Unit) {
        viewModel.requestPopularMovies("US") // Ajusta la región según sea necesario
    }

    val progressVisible by viewModel.progressVisible.collectAsState()
    val popularMovies by viewModel.listPopularMovies.collectAsState()
    val showMessage by viewModel.showMessage.collectAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (progressVisible) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyColumn {
                items(popularMovies) { movie ->
                    MovieItem(movie) { selectedMovie ->
                        viewModel.onMovieClicked(selectedMovie)
                    }
                }
            }
        }

        if (showMessage.isNotEmpty()) {
            Text(
                text = showMessage,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                // style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: (Movie) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                onClick(movie)
            },
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = movie.title,
                // style = MaterialTheme.typography.h6
            )
            Text(
                text = movie.overview,
                // style = MaterialTheme.typography.body2
            )
        }
    }
}
