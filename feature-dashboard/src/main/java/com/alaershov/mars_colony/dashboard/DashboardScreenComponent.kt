package com.alaershov.mars_colony.dashboard

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow

interface DashboardScreenComponent {

    val state: StateFlow<DashboardScreenState>

    fun onHabitatClick()

    fun onPowerClick()

    interface Factory {
        fun create(
            componentContext: ComponentContext,
            navigateToHabitatList: () -> Unit,
            navigateToPowerPlantList: () -> Unit,
        ): DashboardScreenComponent
    }
}
