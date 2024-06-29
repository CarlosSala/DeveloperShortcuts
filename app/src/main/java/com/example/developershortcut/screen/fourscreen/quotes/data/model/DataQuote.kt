package com.example.developershortcut.screen.fourscreen.quotes.data.model

import com.google.gson.annotations.SerializedName


data class DataQuote(
    @SerializedName("author") val author: String,
    @SerializedName("category") val category: String,
    @SerializedName("context") val context: String,
    @SerializedName("date") val date: String,
    @SerializedName("id") val id: Int,
    @SerializedName("language") val language: String,
    @SerializedName("quote") val quote: String,
    @SerializedName("source") val source: String,
    @SerializedName("tags") val tags: List<String>
)

/*

data class MovieDbResult (
    val page :Int,
    val results :List<Movie>,
    val total_pages :Int,
    val total_results :Int
)*/
