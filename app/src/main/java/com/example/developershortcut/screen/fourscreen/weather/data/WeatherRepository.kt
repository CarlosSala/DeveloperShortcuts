package com.example.developershortcut.screen.fourscreen.weather.data

import com.example.developershortcut.screen.fourscreen.weather.data.repository.network.RemoteDataSourceWeather
import com.example.developershortcut.screen.fourscreen.weather.domain.model.WeatherModel

class WeatherRepository(private val remoteWeatherDataSource: RemoteDataSourceWeather) {

    suspend fun getWeather(): WeatherModel {

        return remoteWeatherDataSource.getWeather()
    }
}