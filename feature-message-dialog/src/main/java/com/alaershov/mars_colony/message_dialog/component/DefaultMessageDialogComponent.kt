package com.alaershov.mars_colony.message_dialog.component

import com.alaershov.mars_colony.message_dialog.MessageDialogState
import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultMessageDialogComponent @AssistedInject constructor(
    @Assisted
    componentContext: ComponentContext,
    @Assisted
    dialogState: MessageDialogState,
    @Assisted
    private val onButtonClick: () -> Unit,
) : MessageDialogComponent, ComponentContext by componentContext {

    override val state = MutableStateFlow(dialogState)

    override val bottomSheetContentState = state

    override fun onButtonClick() {
        onButtonClick.invoke()
    }

    @AssistedFactory
    interface Factory : MessageDialogComponent.Factory {

        override fun create(
            componentContext: ComponentContext,
            dialogState: MessageDialogState,
            onButtonClick: () -> Unit,
        ): DefaultMessageDialogComponent
    }
}
