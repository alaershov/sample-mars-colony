package com.alaershov.bottomsheet_decompose_sample.confirm_dialog

import com.alaershov.bottomsheet_decompose_sample.bottomsheet.BottomSheetContentComponent
import kotlinx.coroutines.flow.StateFlow

interface ConfirmContentComponent : BottomSheetContentComponent {

    val state: StateFlow<ConfirmDialogState>

    fun onYesClicked()

    fun onNoClicked()

    fun onInfoClicked()
}
