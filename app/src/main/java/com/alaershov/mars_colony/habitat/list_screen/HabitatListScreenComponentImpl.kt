package com.alaershov.mars_colony.habitat.list_screen

import com.alaershov.mars_colony.habitat.HabitatRepository
import com.alaershov.mars_colony.habitat.totalCapacity
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HabitatListScreenComponentImpl(
    componentContext: ComponentContext,
    habitatRepository: HabitatRepository,
    private val onBuildClick: () -> Unit,
    private val onDismantleHabitatClick: (String) -> Unit,
) : HabitatListScreenComponent, ComponentContext by componentContext {

    private val _state = MutableStateFlow(
        HabitatListScreenState(
            list = emptyList(),
            totalCapacity = 0
        )
    )

    override val state: StateFlow<HabitatListScreenState> = _state

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    init {
        habitatRepository.state
            .onEach { habitatState ->
                _state.value = HabitatListScreenState(
                    list = habitatState.habitatList,
                    totalCapacity = habitatState.totalCapacity
                )
            }
            .launchIn(scope)
    }

    override fun onBuildClick() {
        onBuildClick.invoke()
    }

    override fun onHabitatClick(id: String) {
        onDismantleHabitatClick.invoke(id)
    }
}
