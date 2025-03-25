package com.alaershov.mars_colony.dashboard.component

import com.alaershov.mars_colony.dashboard.DashboardScreenState
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow

interface DashboardScreenComponent {

    val state: StateFlow<DashboardScreenState>

    fun onHabitatClick()

    fun onPowerClick()

    /**
     * Интерфейс фабрики для создания экземпляра этого компонента.
     *
     * Не привязан к DI фреймворку и вообще не знает о его существовании.
     */
    interface Factory {
        fun create(
            componentContext: ComponentContext,
            navigateToHabitatList: () -> Unit,
            navigateToPowerPlantList: () -> Unit,
        ): DashboardScreenComponent
    }
}
