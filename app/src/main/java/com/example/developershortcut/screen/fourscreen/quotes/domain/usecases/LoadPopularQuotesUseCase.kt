package com.example.developershortcut.screen.fourscreen.quotes.domain.usecases

import com.example.developershortcut.screen.fourscreen.quotes.data.repository.QuoteRepository
import com.example.developershortcut.screen.fourscreen.quotes.domain.model.DomainQuote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadPopularQuotesUseCase(private val quoteRepository: QuoteRepository) {

    suspend fun loadPopularQuotes(): List<DomainQuote> = withContext(Dispatchers.IO) {

        quoteRepository.getQuotes()
    }
}