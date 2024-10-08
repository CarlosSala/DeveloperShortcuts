package com.example.developershortcut.screen.fourscreen.quotes.data.repository.network

import com.example.developershortcut.screen.fourscreen.quotes.domain.model.DomainQuote


interface RemoteDataSource {

    suspend fun getPopularQuotes(): List<DomainQuote>
}