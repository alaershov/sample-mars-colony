package com.alaershov.mars_colony.dashboard.component

import com.alaershov.mars_colony.dashboard.DashboardScreenState
import com.alaershov.mars_colony.shared.weather.WeatherState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PreviewDashboardScreenComponent : DashboardScreenComponent {
    override val state: StateFlow<DashboardScreenState> = MutableStateFlow(
        DashboardScreenState(
            totalCapacity = 15,
            totalPower = 25000,
            weatherState = WeatherState(
                temperature = 27.5,
                windSpeed = 4.3,
                solarPower = 107.9,
            )
        )
    )

    override fun onHabitatClick() {}

    override fun onPowerClick() {}
}
