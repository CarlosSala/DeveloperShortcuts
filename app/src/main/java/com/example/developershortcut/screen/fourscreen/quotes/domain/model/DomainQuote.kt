package com.example.developershortcut.screen.fourscreen.quotes.domain.model

// a parcelable object can be passed the most easy way

data class DomainQuote(
    val author: String,
    val category: String,
    val context: String,
    val date: String,
    val id: Int,
    val language: String,
    val quote: String,
    val source: String,
    val tags: List<String>
)

// mapper
/*
fun QuoteModel.toDomain() = Quote(quote, author)
fun QuoteEntity.toDomain() = Quote(quote, author)*/
