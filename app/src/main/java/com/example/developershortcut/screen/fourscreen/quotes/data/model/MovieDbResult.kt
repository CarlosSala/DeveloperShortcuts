package com.example.developershortcut.screen.fourscreen.quotes.data.model

import com.example.developershortcut.screen.fourscreen.quotes.domain.model.Movie


data class MovieDbResult (
    val page :Int,
    val results :List<Movie>,
    val total_pages :Int,
    val total_results :Int
)