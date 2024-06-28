package com.example.developershortcut.screen.fourscreen.quotes.data.repository.network

import com.example.developershortcut.screen.fourscreen.quotes.data.model.MovieDbResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiClient {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") page: String
    ): MovieDbResult
}