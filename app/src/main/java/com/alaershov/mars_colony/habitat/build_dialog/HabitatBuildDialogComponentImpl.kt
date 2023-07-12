package com.alaershov.mars_colony.habitat.build_dialog

import com.alaershov.mars_colony.bottomsheet.BottomSheetContentState
import com.alaershov.mars_colony.habitat.HabitatRepository
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HabitatBuildDialogComponentImpl(
    private val componentContext: ComponentContext,
    private val habitatRepository: HabitatRepository,
    private val onDismissed: () -> Unit
) : HabitatBuildDialogComponent, ComponentContext by componentContext {

    private val _state = MutableStateFlow(
        HabitatBuildDialogState(
            capacity = 1,
            isProgress = false
        )
    )

    override val state: StateFlow<HabitatBuildDialogState> = _state

    private val _bottomSheetContentState = MutableStateFlow(
        BottomSheetContentState(isDismissAllowed = true)
    )
    override val bottomSheetContentState: StateFlow<BottomSheetContentState> =
        _bottomSheetContentState

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onPlusClick() {
        val value = _state.value
        _state.value = value.copy(capacity = value.capacity + 1)
    }

    override fun onMinusClick() {
        val value = _state.value
        _state.value = value.copy(
            capacity = (value.capacity - 1).coerceAtLeast(1)
        )
    }

    override fun onBuildClick() {
        if (state.value.isProgress) return

        scope.launch {
            _bottomSheetContentState.value = BottomSheetContentState(
                isDismissAllowed = false
            )
            _state.value = state.value.copy(
                isProgress = true
            )

            habitatRepository.buildHabitat(state.value.capacity)

            _bottomSheetContentState.value = BottomSheetContentState(
                isDismissAllowed = true
            )
            _state.value = state.value.copy(
                isProgress = false
            )

            onDismissed()
        }
    }
}