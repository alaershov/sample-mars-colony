package com.alaershov.mars_colony.message_dialog.component

import com.alaershov.mars_colony.message_dialog.MessageDialogState
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultMessageDialogComponent internal constructor(
    componentContext: ComponentContext,
    dialogState: MessageDialogState,
    private val onButtonClick: () -> Unit,
) : MessageDialogComponent, ComponentContext by componentContext {

    override val state = MutableStateFlow(dialogState)

    override val bottomSheetContentState = state

    override fun onButtonClick() {
        onButtonClick.invoke()
    }

    interface Factory : MessageDialogComponent.Factory
}
