package com.example.developershortcut.screen.fourscreen.weather.data.repository.network

import com.example.developershortcut.screen.fourscreen.weather.domain.model.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiClient {

    @GET("weather?")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String,
    ): WeatherModel
}