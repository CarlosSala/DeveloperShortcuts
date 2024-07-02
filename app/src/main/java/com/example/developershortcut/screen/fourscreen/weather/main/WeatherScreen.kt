package com.example.developershortcut.screen.fourscreen.weather.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.developershortcut.R
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
            .padding(16.dp)
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
                WeatherUI(it)
            }
        }
    }
}

@Composable
fun WeatherUI(weather: WeatherModel) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2196F3),
                        Color(0xFF03A9F4),
                        Color(0xFF00BCD4)
                    )
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Current City: ${weather.name}",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Last Update: ${weather.dt}",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TextField(
            value = searchQuery,
            onValueChange = { newValue -> searchQuery = newValue },
            placeholder = { Text(text = "Search Cities") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "${weather.main.temp}°C", fontSize = 48.sp, color = Color.White)
            Image(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "State Weather",
                modifier = Modifier.size(64.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Feels like: ${weather.main.feels_like}°C", fontSize = 18.sp, color = Color.White)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Min: ${weather.main.temp_min}°C", fontSize = 18.sp, color = Color.White)
            Text(text = "Max: ${weather.main.temp_max}°C", fontSize = 18.sp, color = Color.White)
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                DetailIconWithText(iconResId = R.drawable.wind, detail = "Wind speed: ${weather.wind.speed} KM/H")
                DetailIconWithText(iconResId = R.drawable.humidity, detail = "Humidity: ${weather.main.humidity} %")
                DetailIconWithText(iconResId = R.drawable.smoke, detail = "Air Quality: Good")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                DetailIconWithText(iconResId = R.drawable.pressure, detail = "Pressure: 1013 hPa")
                DetailIconWithText(iconResId = R.drawable.sunset, detail = "Sunset: 10 km")
                DetailIconWithText(iconResId = R.drawable.sunrise, detail = "sunrise: 5")
            }
        }
    }
}

@Composable
fun DetailIconWithText(iconResId: Int, detail: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = detail,
            modifier = Modifier.size(40.dp)
        )
        Text(text = detail, fontSize = 14.sp, color = Color.White)
    }
}

/*

@Composable
fun WeatherItem(weather: WeatherModel) {

    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Text(
        text = "Current City: ${weather.name}",
        fontSize = 24.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )
    Text(
        text = "Last Update: ${weather.dt}",
        fontSize = 16.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )
    TextField(
        value = searchQuery,
        onValueChange = { newValue -> searchQuery = newValue },
        placeholder = { Text(text = "Search Cities") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${weather.main.temp}°C", fontSize = 48.sp)
        Image(
            painter = painterResource(id = R.drawable.ic_location_on_24),
            contentDescription = "State Weather",
            modifier = Modifier.size(64.dp)
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${weather.main.feels_like}°C", fontSize = 18.sp)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Min: ${weather.main.temp_min}°C", fontSize = 18.sp)
        Text(text = "Max: ${weather.main.temp_max}°C", fontSize = 18.sp)
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
         Row(
             modifier = Modifier.fillMaxWidth(),
             horizontalArrangement = Arrangement.SpaceAround
         ) {
             DetailIconWithText(iconResId = R.drawable.ic_notes_24, detail = "Wind speed: ${weather.wind.speed} KM/H")
             DetailIconWithText(iconResId = R.drawable.ic_call_24, detail = "Humidity: ${weather.main.humidity} %")
             DetailIconWithText(iconResId = R.drawable.ic_email_24, detail = "Calidad del aire: Buena")
         }
         Row(
             modifier = Modifier.fillMaxWidth(),
             horizontalArrangement = Arrangement.SpaceAround
         ) {
             DetailIconWithText(iconResId = R.drawable.ic_sms_24, detail = "Presión: 1013 hPa")
             DetailIconWithText(iconResId = R.drawable.ic_date_range_24, detail = "Visibilidad: 10 km")
             DetailIconWithText(iconResId = R.drawable.ic_volume_up_24, detail = "Índice UV: 5")
         }
    }
}

@Composable
fun DetailIconWithText(iconResId: Int, detail: String) {
    Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = detail,
            modifier = Modifier.size(40.dp)
        )
        Text(text = detail, fontSize = 14.sp)
    }
}*/
