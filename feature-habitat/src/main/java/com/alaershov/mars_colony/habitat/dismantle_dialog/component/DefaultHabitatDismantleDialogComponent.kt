package com.alaershov.mars_colony.habitat.dismantle_dialog.component

import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentState
import com.alaershov.mars_colony.habitat.HabitatRepository
import com.alaershov.mars_colony.habitat.dismantle_dialog.HabitatDismantleDialogState
import com.alaershov.mars_colony.habitat.totalCapacity
import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DefaultHabitatDismantleDialogComponent @AssistedInject internal constructor(
    @Assisted
    componentContext: ComponentContext,
    @Assisted("habitatId")
    habitatId: String,
    @Assisted("onConfirmationNeeded")
    private val onConfirmationNeeded: () -> Unit,
    @Assisted("onDismiss")
    private val onDismiss: () -> Unit,
    private val habitatRepository: HabitatRepository,
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

    override fun onDismantleClick() {
        onConfirmationNeeded()

    }

    override fun confirm() {
        state.value.habitat?.id?.let { id ->
            habitatRepository.dismantleHabitat(id)
        }
        onDismiss()
    }

    @AssistedFactory
    interface Factory : HabitatDismantleDialogComponent.Factory {

        override fun create(
            componentContext: ComponentContext,
            @Assisted("habitatId")
            habitatId: String,
            @Assisted("onConfirmationNeeded")
            onConfirmationNeeded: () -> Unit,
            @Assisted("onDismiss")
            onDismiss: () -> Unit,
        ): DefaultHabitatDismantleDialogComponent
    }
}
