package com.alaershov.mars_colony.dashboard

import com.alaershov.mars_colony.shared.weather.WeatherState

data class DashboardScreenState(
    val totalCapacity: Int,
    val totalPower: Int,
    val weatherState: WeatherState,
)
