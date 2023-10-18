package com.alaershov.mars_colony.dashboard

import com.alaershov.mars_colony.habitat.HabitatRepository
import com.alaershov.mars_colony.habitat.totalCapacity
import com.alaershov.mars_colony.power.PowerPlantRepository
import com.alaershov.mars_colony.power.totalPower
import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DefaultDashboardScreenComponent @AssistedInject constructor(
    habitatRepository: HabitatRepository,
    powerPlantRepository: PowerPlantRepository,
    @Assisted
    componentContext: ComponentContext,
    @Assisted("navigateToHabitatList")
    private val navigateToHabitatList: () -> Unit,
    @Assisted("navigateToPowerPlantList")
    private val navigateToPowerPlantList: () -> Unit,
) : DashboardScreenComponent,
    ComponentContext by componentContext {

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
                totalCapacity = habitatState.totalCapacity,
                totalPower = powerPlantState.totalPower,
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
        navigateToPowerPlantList()
    }

    @AssistedFactory
    interface Factory : DashboardScreenComponent.Factory {
        override fun create(
            componentContext: ComponentContext,
            @Assisted("navigateToHabitatList")
            navigateToHabitatList: () -> Unit,
            @Assisted("navigateToPowerPlantList")
            navigateToPowerPlantList: () -> Unit,
        ): DefaultDashboardScreenComponent
    }
}
