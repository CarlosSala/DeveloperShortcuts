package com.example.developershortcut.screen.fourscreen.weather.common

import com.example.developershortcut.screen.fourscreen.weather.data.repository.network.RemoteDataSourceWeather
import com.example.developershortcut.screen.fourscreen.weather.data.repository.network.WeatherDbClient
import com.example.developershortcut.screen.fourscreen.weather.domain.model.WeatherModel

class ServerWeatherDataSource : RemoteDataSourceWeather {

    override suspend fun getWeather(region: String): WeatherModel {
        val weather = WeatherDbClient.service.getWeather(
            city = region,
            apiKey = "dabb2d5bc8c2a104fb325acef1607cfe",
            units = "metric"
        )
        return weather
    }
}