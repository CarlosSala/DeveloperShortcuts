package com.example.developershortcut.screen.fourscreen.quotes.common

import com.example.developershortcut.screen.fourscreen.quotes.data.repository.network.QuotesDbClient
import com.example.developershortcut.screen.fourscreen.quotes.data.repository.network.RemoteDataSource
import com.example.developershortcut.screen.fourscreen.quotes.domain.model.DomainQuote

class ServerMovieDataSource : RemoteDataSource {

    // this result it will be used by other who will be using this
    override suspend fun getPopularQuotes(): List<DomainQuote> {

        val listQuotes = QuotesDbClient.service.getPopularQuotes()
        return listQuotes
    }
}