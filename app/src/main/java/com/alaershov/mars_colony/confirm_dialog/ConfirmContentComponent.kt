package com.alaershov.mars_colony.confirm_dialog

import com.alaershov.mars_colony.bottomsheet.BottomSheetContentComponent
import kotlinx.coroutines.flow.StateFlow

interface ConfirmContentComponent : BottomSheetContentComponent {

    val state: StateFlow<ConfirmDialogState>

    fun onYesClicked()

    fun onNoClicked()

    fun onInfoClicked()
}
