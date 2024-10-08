package com.example.developershortcut.screen.fourscreen.quotes.main

import com.example.developershortcut.screen.fourscreen.quotes.common.ServerQuotesDataSource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.developershortcut.screen.fourscreen.quotes.data.repository.QuoteRepository
import com.example.developershortcut.screen.fourscreen.quotes.domain.model.DomainQuote
import com.example.developershortcut.screen.fourscreen.quotes.domain.usecases.LoadPopularQuotesUseCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuoteViewModel() : ViewModel() {

    private val loadPopularQuotesUseCase = LoadPopularQuotesUseCase(
        QuoteRepository(ServerQuotesDataSource())
    )

    private val _progressVisible = MutableStateFlow(false)
    val progressVisible: StateFlow<Boolean> get() = _progressVisible

    private val _listPopularQuotes = MutableStateFlow<List<DomainQuote>>(emptyList())
    val listPopularMovies: StateFlow<List<DomainQuote>> get() = _listPopularQuotes

    private val _showMessage = MutableStateFlow<String>("")
    val showMessage: StateFlow<String> get() = _showMessage
    /*
        private val _showMessage = MutableSharedFlow<String>()
        val showMessage: SharedFlow<String> get() = _showMessage
    */


    fun requestPopularQuotes() {

        viewModelScope.launch(Dispatchers.IO) {
            _progressVisible.value = true
            _listPopularQuotes.value = loadPopularQuotesUseCase.loadPopularQuotes()
            _progressVisible.value = false
        }
    }

    fun onQuoteClicked(domainQuote: DomainQuote) {

        viewModelScope.launch {
            _showMessage.emit(domainQuote.language)
        }
    }
}