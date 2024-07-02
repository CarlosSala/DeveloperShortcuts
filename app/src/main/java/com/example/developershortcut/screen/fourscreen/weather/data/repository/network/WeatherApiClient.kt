package com.example.developershortcut.screen.fourscreen.weather.data.repository.network

import com.example.developershortcut.screen.fourscreen.weather.domain.model.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiClient {

    @GET("weather?units=metric&lang=es")
    suspend fun getWeather(
        @Query("id") region: String,
        @Query("appid") apiKey: String
    ): WeatherModel
}
    // @GET("weather?id=3871336&appid=dabb2d5bc8c2a104fb325acef1607cfe&units=metric&lang=es")
