package com.alaershov.mars_colony.confirm_dialog

import com.alaershov.mars_colony.bottomsheet.BottomSheetContentState
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ConfirmContentComponentImpl(
    private val componentContext: ComponentContext,
    question: String,
    private val onDismissed: () -> Unit,
    private val onInfoClicked: () -> Unit,
) : ConfirmContentComponent {

    override val state: StateFlow<ConfirmDialogState> = MutableStateFlow(
        ConfirmDialogState(question)
    )

    override val bottomSheetContentState: StateFlow<BottomSheetContentState> = MutableStateFlow(
        BottomSheetContentState(true)
    )

    override fun onYesClicked() {
        onDismissed()
    }

    override fun onNoClicked() {
    }

    override fun onInfoClicked() {
        onInfoClicked.invoke()
    }
}
