package com.alaershov.mars_colony.message_dialog.component

import com.alaershov.mars_colony.message_dialog.MessageDialogState
import kotlinx.coroutines.flow.MutableStateFlow

class PreviewMessageDialogComponent : MessageDialogComponent {

    override val state = MutableStateFlow(
        MessageDialogState(
            message = "Preview Message",
            button = "Click me!"
        )
    )

    override val bottomSheetContentState = state

    override fun onButtonClick() {}
}
