package com.example.developershortcut.screen.fourscreen.quotes.usecases

import com.example.developershortcut.screen.fourscreen.quotes.data.repository.MovieRepository
import com.example.developershortcut.screen.fourscreen.quotes.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadPopularMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend fun loadPopularMovies(region: String): List<Movie> = withContext(Dispatchers.IO) {

        movieRepository.getMovies(region)
    }
}