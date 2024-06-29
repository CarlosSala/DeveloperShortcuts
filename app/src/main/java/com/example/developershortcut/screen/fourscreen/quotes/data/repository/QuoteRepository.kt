package com.example.developershortcut.screen.fourscreen.quotes.data.repository

import com.example.developershortcut.screen.fourscreen.quotes.data.repository.network.RemoteDataSource
import com.example.developershortcut.screen.fourscreen.quotes.domain.model.DomainQuote

class QuoteRepository(private val remoteDataSource: RemoteDataSource) {

    // someone it is going to use this method and I am going to use this result

    suspend fun getQuotes(): List<DomainQuote> {

        return remoteDataSource.getPopularQuotes()
    }
}