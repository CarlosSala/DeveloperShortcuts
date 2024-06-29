package com.example.developershortcut.screen.fourscreen.quotes.data.repository.network

import com.example.developershortcut.screen.fourscreen.quotes.domain.model.DomainQuote
import retrofit2.http.GET

interface QuotesApiClient {

    @GET("quote-response-json/developer_quotes")
    suspend fun getPopularQuotes(
    ): List<DomainQuote>
}