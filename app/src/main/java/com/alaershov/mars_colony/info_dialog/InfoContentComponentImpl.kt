package com.alaershov.mars_colony.info_dialog

import com.alaershov.mars_colony.bottomsheet.BottomSheetContentState
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InfoContentComponentImpl(
    private val componentContext: ComponentContext,
    private val onDismissed: () -> Unit,
    private val onBuyClicked: () -> Unit,
) : InfoContentComponent, ComponentContext by componentContext {

    override val state: StateFlow<InfoDialogState> = MutableStateFlow(
        InfoDialogState(
            (1..10).map { "Information Item $it" }
        )
    )

    private val _bottomSheetContentState = MutableStateFlow(
        BottomSheetContentState(isDismissAllowed = false)
    )
    override val bottomSheetContentState: StateFlow<BottomSheetContentState> =
        _bottomSheetContentState

    override fun onBuyClicked() {
        this.onBuyClicked.invoke()
    }

    override fun onDismissClicked() {
        _bottomSheetContentState.value = _bottomSheetContentState.value.copy(
            isDismissAllowed = true
        )
        onDismissed()
    }
}
