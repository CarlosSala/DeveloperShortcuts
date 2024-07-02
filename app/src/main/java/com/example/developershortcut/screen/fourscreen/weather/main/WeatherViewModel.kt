package com.example.developershortcut.screen.fourscreen.weather.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.developershortcut.screen.fourscreen.weather.common.ServerWeatherDataSource
import com.example.developershortcut.screen.fourscreen.weather.data.WeatherRepository
import com.example.developershortcut.screen.fourscreen.weather.domain.model.WeatherModel
import com.example.developershortcut.screen.fourscreen.weather.domain.usecases.LoadWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val loadWeatherUseCase =
        LoadWeatherUseCase(WeatherRepository(ServerWeatherDataSource()))

    private val _progressVisible = MutableStateFlow(false)
    val progressVisible: StateFlow<Boolean> = _progressVisible

    private val _getWeather = MutableStateFlow<WeatherModel?>(null)
    val getWeather: StateFlow<WeatherModel?> = _getWeather

    fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressVisible.value = true
            _getWeather.value = loadWeatherUseCase.getWeather()
            _progressVisible.value = false
        }
    }
}