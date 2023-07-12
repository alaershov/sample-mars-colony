package com.alaershov.mars_colony.dashboard_screen

import com.alaershov.mars_colony.habitat.HabitatRepository
import com.alaershov.mars_colony.power.PowerPlantRepository
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DashboardScreenComponentImpl(
    componentContext: ComponentContext,
    habitatRepository: HabitatRepository,
    powerPlantRepository: PowerPlantRepository,
    private val navigateToHabitatList: () -> Unit,
    private val navigateToPowerPlantList: () -> Unit,
) : DashboardScreenComponent, ComponentContext by componentContext {

    private val _state: MutableStateFlow<DashboardScreenState> = MutableStateFlow(
        DashboardScreenState(
            totalCapacity = 0,
            totalPower = 0
        )
    )

    override val state: StateFlow<DashboardScreenState> = _state

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    init {
        combine(
            habitatRepository.state,
            powerPlantRepository.state,
        ) { habitatState, powerPlantState ->
            DashboardScreenState(
                totalCapacity = habitatState.habitatList.sumOf { it.capacity },
                totalPower = powerPlantState.powerPlantList.sumOf { it.power },
            )
        }
            .onEach { dashboardScreenState ->
                _state.value = dashboardScreenState
            }
            .launchIn(scope)
    }

    override fun onHabitatClick() {
        navigateToHabitatList()
    }

    override fun onPowerClick() {
        navigateToPowerPlantList
    }
}
