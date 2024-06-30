package com.example.developershortcut.screen.fourscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.developershortcut.screen.fourscreen.news.NewsScreen
import com.example.developershortcut.screen.fourscreen.systeminfo.SystemInfoScreen
import com.example.developershortcut.screen.fourscreen.quotes.main.QuotesScreen
import com.example.developershortcut.screen.fourscreen.quotes.main.navigation.QuoteNavigation
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabRowManagerScreen(paddingValues: PaddingValues) {

    val viewModel: TabRowViewModel = viewModel()
    val screenTitles = listOf("News", "Quotes", "About System")
    val pagerState = rememberPagerState(initialPage = viewModel.currentPage, pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            // backgroundColor = MaterialTheme.colorScheme.surface,
            // contentColor = MaterialTheme.colorScheme.onSurface
        ) {
            screenTitles.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        viewModel.mySetCurrentPage(index)
                        // Animate swipe screen
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(title) }
                )
            }
        }

        // pages content
        HorizontalPager(
            // count = screenTitles.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> NewsScreen()
                1 -> QuoteNavigation()
                2 -> SystemInfoScreen()
            }
        }

        // Update ViewModel when swipe
        LaunchedEffect(pagerState.currentPage) {
            viewModel.mySetCurrentPage(pagerState.currentPage)
        }
    }
}


/*    val viewModel: MenuViewModel = viewModel()
    val pagerState = rememberPagerState(0)

    Column(
        Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            count = 3,
            modifier = Modifier.weight(1f),
            state = pagerState
        ) { page ->

            when (page) {
                0 -> Screen1()
                1 -> Screen2()
                2 -> Screen3()
            }

            // viewModel.mySetCurrentPage(page)
        }

       // PagesTab(viewModel = viewModel)
    }
}*/
