package com.example.developershortcut.screen.fourscreen

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

class TabRowViewModel : ViewModel() {

    var currentPage by mutableIntStateOf(0)
        private set

    fun mySetCurrentPage(page: Int) {
        currentPage = page
    }
}
