package com.alaershov.mars_colony.habitat.dismantle_dialog

import com.alaershov.mars_colony.bottomsheet.BottomSheetContentState
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

class HabitatDismantleDialogComponentImpl(
    componentContext: ComponentContext,
    habitatId: String,
    private val habitatRepository: HabitatRepository,
    private val onDismiss: () -> Unit,
) : HabitatDismantleDialogComponent, ComponentContext by componentContext {

    private val _state = MutableStateFlow(
        HabitatDismantleDialogState(
            habitat = null,
            capacityCurrent = 0,
            capacityAfterDismantle = 0,
        )
    )

    override val state: StateFlow<HabitatDismantleDialogState> = _state

    override val bottomSheetContentState: StateFlow<BottomSheetContentState> = state

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    init {
        habitatRepository.state
            .onEach { habitatState ->
                val habitat = habitatState.habitatList.find { it.id == habitatId }
                _state.value = HabitatDismantleDialogState(
                    habitat = habitat,
                    capacityCurrent = habitatState.totalCapacity,
                    capacityAfterDismantle = habitatState.totalCapacity - (habitat?.capacity ?: 0),
                )
            }
            .launchIn(scope)
    }

    override fun onConfirmClick() {
        state.value.habitat?.id?.let { id ->
            habitatRepository.dismantleHabitat(id)
        }
        onDismiss()
    }
}
