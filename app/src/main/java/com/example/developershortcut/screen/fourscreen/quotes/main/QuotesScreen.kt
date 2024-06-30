package com.example.developershortcut.screen.fourscreen.quotes.main

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.developershortcut.navigation.AppScreens
import com.example.developershortcut.screen.fourscreen.quotes.domain.model.DomainQuote
import com.example.developershortcut.screen.fourscreen.quotes.main.detail.QuoteDetailScreen

@Composable
fun QuotesScreen(navController: NavHostController) {

    val viewModel: QuoteViewModel = viewModel()
    val context: Context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.requestPopularQuotes()
    }

    val progressVisible by viewModel.progressVisible.collectAsState()
    val popularQuotes by viewModel.listPopularMovies.collectAsState()
    // val showMessage by viewModel.showMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        if (progressVisible) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 16.dp),
                color = Color.Gray
            )
        } else {
            LazyColumn {
                items(popularQuotes) { quote ->
                    QuoteItem(navController, quote) { selectedQuote ->
                        viewModel.onQuoteClicked(selectedQuote)
                        Toast.makeText(context, selectedQuote.source, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        /*       if (showMessage.isNotEmpty()) {
                   Text(
                       text = showMessage,
                       modifier = Modifier.align(Alignment.CenterHorizontally),
                       // style = MaterialTheme.typography.h6
                   )
               }*/
    }
}

@Composable
fun QuoteItem(
    navController: NavHostController,
    domainQuote: DomainQuote,
    onClick: (DomainQuote) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {

                val myRoute = "${AppScreens.QuoteDetailScreen.route}/${domainQuote.id}"

                navController.navigate(myRoute)
                // onClick(domainQuote)
            },
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = domainQuote.author,
                // style = MaterialTheme.typography.h6
            )
            Text(
                text = domainQuote.category,
                // style = MaterialTheme.typography.body2
            )
        }
    }
}
