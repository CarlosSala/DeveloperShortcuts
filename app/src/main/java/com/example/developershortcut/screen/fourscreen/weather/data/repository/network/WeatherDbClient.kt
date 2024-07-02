package com.example.developershortcut.screen.fourscreen.weather.data.repository.network

import com.example.developershortcut.screen.fourscreen.weather.domain.model.WeatherModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherDbClient {

    private val retrofit = Retrofit
        .Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service: WeatherApiClient = retrofit.create(WeatherApiClient::class.java)
}