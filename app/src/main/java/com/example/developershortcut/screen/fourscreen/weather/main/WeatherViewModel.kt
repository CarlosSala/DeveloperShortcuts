package com.example.developershortcut.screen.fourscreen.weather.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.developershortcut.screen.fourscreen.weather.common.NewSys
import com.example.developershortcut.screen.fourscreen.weather.common.NewWeatherModel
import com.example.developershortcut.screen.fourscreen.weather.common.ServerWeatherDataSource
import com.example.developershortcut.screen.fourscreen.weather.common.toNewWeatherModel
import com.example.developershortcut.screen.fourscreen.weather.data.WeatherRepository
import com.example.developershortcut.screen.fourscreen.weather.domain.model.Clouds
import com.example.developershortcut.screen.fourscreen.weather.domain.model.Coord
import com.example.developershortcut.screen.fourscreen.weather.domain.model.Main
import com.example.developershortcut.screen.fourscreen.weather.domain.model.Weather
import com.example.developershortcut.screen.fourscreen.weather.domain.model.WeatherModel
import com.example.developershortcut.screen.fourscreen.weather.domain.model.Wind
import com.example.developershortcut.screen.fourscreen.weather.domain.usecases.LoadWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WeatherViewModel : ViewModel() {

    private val loadWeatherUseCase =
        LoadWeatherUseCase(WeatherRepository(ServerWeatherDataSource()))

    private val _progressVisible = MutableStateFlow(false)
    val progressVisible: StateFlow<Boolean> = _progressVisible

    private val _getWeather = MutableStateFlow<WeatherModel?>(null)
    val getWeather: StateFlow<WeatherModel?> = _getWeather

    private val _getConverterValuesWeather = MutableStateFlow<List<String>?>(null)
    val getConverterValuesWeather: StateFlow<List<String>?> = _getConverterValuesWeather

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    fun getWeather(region: String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _progressVisible.value = true
                _getWeather.value = loadWeatherUseCase.getWeather(region)
                _getConverterValuesWeather.value = _getWeather.value?.let { transformValues(it) }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _progressVisible.value = false
            }
        }
    }

    private fun transformValues(weatherModel: WeatherModel): List<String> {

        val myMutableList: MutableList<String> = mutableListOf(
            dateFormatConverter(weatherModel.dt.toLong()),
            dateFormatConverter(weatherModel.sys.sunset.toLong()),
            dateFormatConverter(weatherModel.sys.sunrise.toLong())
        )

        return myMutableList
    }

    private fun dateFormatConverter(date: Long): String {

        return SimpleDateFormat(
            "hh:mm a",
            Locale.ENGLISH
        ).format(Date(date * 1000))
    }
}