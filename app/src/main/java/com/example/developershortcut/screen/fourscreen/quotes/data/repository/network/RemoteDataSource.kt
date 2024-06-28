package com.example.developershortcut.screen.fourscreen.quotes.data.repository.network

import com.example.developershortcut.screen.fourscreen.quotes.domain.model.Movie

interface RemoteDataSource {

    suspend fun getPopularMovies(region:String): List<Movie>
}