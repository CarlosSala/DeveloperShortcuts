package com.example.developershortcut.screen.fourscreen.weather.main

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.developershortcut.navigation.AppScreens
import com.example.developershortcut.screen.fourscreen.quotes.domain.model.DomainQuote
import com.example.developershortcut.screen.fourscreen.quotes.main.QuoteItem
import com.example.developershortcut.screen.fourscreen.weather.domain.model.Weather
import com.example.developershortcut.screen.fourscreen.weather.domain.model.WeatherModel

@Composable
fun WeatherScreen() {

    val viewModel: WeatherViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.getWeather()
    }

    val progressVisible by viewModel.progressVisible.collectAsState()
    val weather by viewModel.getWeather.collectAsState()

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

            weather?.let {
                WeatherItem(it)
            }
        }
    }
}

@Composable
fun WeatherItem(weather: WeatherModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = weather.name,
                // style = MaterialTheme.typography.h6
            )
            Text(
                text = weather.base,
                // style = MaterialTheme.typography.body2
            )
            Text(
                text = weather.main.temp.toString(),
                // style = MaterialTheme.typography.body2
            )
        }
    }
}