package com.example.developershortcut.screen.fourscreen.weather.common

import com.example.developershortcut.screen.fourscreen.weather.domain.model.Sys
import com.example.developershortcut.screen.fourscreen.weather.domain.model.WeatherModel

data class NewWeatherModel(

    val dt: Int,
    val Sys: Sys
)

fun WeatherModel.toNewWeatherModel(): NewWeatherModel {

    return NewWeatherModel(
        dt = dt,
        Sys = Sys(
            country = sys.country,
            id = sys.id,
            sunrise = sys.sunrise,
            sunset = sys.sunset,
            type = sys.type
        )
    )
}
