package com.example.developershortcut.screen.fourscreen.quotes.data.repository

import com.example.developershortcut.screen.fourscreen.quotes.data.repository.network.RemoteDataSource
import com.example.developershortcut.screen.fourscreen.quotes.domain.model.Movie

class MovieRepository(private val remoteDataSource: RemoteDataSource) {

    // someone it is going to use this method and I am going to use this result

    suspend fun getMovies(region:String): List<Movie> {

        return remoteDataSource.getPopularMovies(region)
    }
}