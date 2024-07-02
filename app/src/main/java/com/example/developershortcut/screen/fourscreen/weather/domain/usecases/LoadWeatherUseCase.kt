package com.example.developershortcut.screen.fourscreen.weather.domain.usecases

import com.example.developershortcut.screen.fourscreen.weather.data.WeatherRepository
import com.example.developershortcut.screen.fourscreen.weather.domain.model.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadWeatherUseCase(private val weatherRepository: WeatherRepository) {

    suspend fun getWeather(region: String): WeatherModel = withContext(Dispatchers.IO) {
        weatherRepository.getWeather(region)
    }
}