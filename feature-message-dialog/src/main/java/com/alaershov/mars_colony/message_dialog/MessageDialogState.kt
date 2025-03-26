package com.alaershov.mars_colony.message_dialog

import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentState

data class MessageDialogState(
    val message: String,
    val button: String,
) : BottomSheetContentState {

    override val isDismissAllowed: Boolean = true
}
