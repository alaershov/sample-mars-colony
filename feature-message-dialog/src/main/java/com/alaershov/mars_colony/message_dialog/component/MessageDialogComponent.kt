package com.alaershov.mars_colony.message_dialog.component

import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.message_dialog.MessageDialogState
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow

interface MessageDialogComponent : BottomSheetContentComponent {

    val state: StateFlow<MessageDialogState>

    fun onButtonClick()

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            dialogState: MessageDialogState,
            onButtonClick: () -> Unit,
        ): MessageDialogComponent
    }
}
