package com.alaershov.mars_colony.dashboard_screen

import kotlinx.coroutines.flow.StateFlow

interface DashboardScreenComponent {

    val state: StateFlow<DashboardScreenState>

    fun onHabitatClick()

    fun onPowerClick()
}
