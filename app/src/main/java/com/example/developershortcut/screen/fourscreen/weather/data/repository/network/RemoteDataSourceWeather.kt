package com.example.developershortcut.screen.fourscreen.weather.data.repository.network

import com.example.developershortcut.screen.fourscreen.weather.domain.model.WeatherModel

interface RemoteDataSourceWeather {

    suspend fun getWeather(): WeatherModel
}